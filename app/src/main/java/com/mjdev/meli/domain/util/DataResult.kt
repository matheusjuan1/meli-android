package com.mjdev.meli.domain.util

import android.util.Log
import com.mjdev.meli.data.remote.model.ApiErrorResponse
import com.mjdev.meli.data.remote.util.AppJson
import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.util.DataResult.Error
import com.mjdev.meli.domain.util.DataResult.Success
import retrofit2.HttpException

private const val TAG = "DataResult"


/**
 * Classe para encapsular o resultado de uma fonte de dados.
 * Pode ser um sucesso [Success] com um valor [T], ou um erro [Error] com uma [MeliException].
 */
sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val error: MeliException) : DataResult<Nothing>()
}

/**
 * Função para realizar chamadas de API de forma segura.
 * Ela captura exceções e retorna um [DataResult] com o resultado da operação.
 *
 * @param apiCall A lambda que executa a chamada real da API.
 * @return Um [DataResult] que pode ser um sucesso ou um erro.
 */
suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): DataResult<T> {
    return try {
        val result = apiCall.invoke()
        Log.d(TAG, "Sucesso na chamada da API")
        Success(result)
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()


        val apiError: ApiErrorResponse? = errorBody?.let {

            try {
                AppJson.decodeFromString<ApiErrorResponse>(it)
            } catch (parseException: Exception) {
                Log.e(
                    TAG,
                    "Falha ao fazer o parse da ApiErrorResponse: ${parseException.localizedMessage}",
                    parseException
                )
                null
            }
        }

        val errorMessage = apiError?.message ?: "Erro HTTP desconhecido."
        val statusCode = e.code()

        val appException = when (e.code()) {
            401 -> MeliException.ApiException.Unauthorized(errorMessage, e)
            403 -> MeliException.ApiException.Forbidden(errorMessage, e)
            404 -> MeliException.ApiException.NotFound(errorMessage, e)
            in 500..599 -> MeliException.ApiException.ServerError(errorMessage, e)
            else -> MeliException.ApiException.UnknownApiError(errorMessage, e, e.code())
        }

        Log.e(
            TAG,
            "Falha na chamada API (HTTP ${statusCode}): ${errorMessage}. Erro completo: $errorBody",
            e
        )
        Error(appException)
    } catch (e: java.io.IOException) {
        Log.e(TAG, "Falha na chamada API (Network): ${e.localizedMessage}", e)
        Error(MeliException.ApiException.NetworkError(e.message ?: "Erro de conexão desconhecido."))
    } catch (e: Exception) {
        Log.e(TAG, "Falha na chamada API (Unknown): ${e.localizedMessage}", e)
        Error(
            MeliException.UnknownException(
                "Ocorreu um erro inesperado: ${e.localizedMessage ?: "Erro desconhecido"}",
                e
            )
        )
    }
}