package com.mjdev.meli.domain.exception

/**
 * MeliException é a classe base para todas as exceções da aplicação.
 * Ela pode ser estendida para criar exceções específicas para diferentes tipos de erros.
 *
 * @property message Mensagem de erro, opcional.
 * @property cause Causa da exceção, opcional.
 */
sealed class MeliException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    /**
     * Exceções relacionadas a chamadas de API ou problemas de rede.
     */
    sealed class ApiException(message: String? = null, cause: Throwable? = null, val statusCode: Int? = null) : MeliException(message, cause) {
        class Unauthorized(message: String? = "Acesso não autorizado", cause: Throwable? = null) : ApiException(message, cause, 401)
        class Forbidden(message: String? = "Você não tem permissão para acessar este recurso.", cause: Throwable? = null) : ApiException(message, cause, 403)
        class NotFound(message: String? = "Recurso não encontrado.", cause: Throwable? = null) : ApiException(message, cause, 404)
        class ServerError(message: String? = "Houve um problema no servidor. Por favor, tente novamente mais tarde.", cause: Throwable? = null) : ApiException(message, cause, 500)
        class NetworkError(message: String? = "Verifique sua conexão com a internet e tente novamente.", cause: Throwable? = null) : ApiException(message, cause)
        class UnknownApiError(message: String? = "Ocorreu um erro inesperado na comunicação com a API.", cause: Throwable? = null, statusCode: Int? = null) : ApiException(message, cause, statusCode)
    }

    /**
     * Exceção para erros inesperados não categorizados.
     */
    class UnknownException(message: String? = "Ocorreu um erro inesperado.", cause: Throwable? = null) : MeliException(message, cause)
}