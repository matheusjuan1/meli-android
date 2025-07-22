package com.mjdev.meli.data.remote.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * AuthInterceptor é um interceptor de requisições HTTP que adiciona o token de acesso
 * ao cabeçalho de autorização.
 *
 * @param accessToken O token de acesso a ser adicionado ao cabeçalho.
 */
class AuthInterceptor(private val accessToken: String) : Interceptor {

    private val TAG = "AuthInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (accessToken.isNotBlank()) {
            val requestWithAuthorization = originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            Log.d(TAG, "Access token adicionado ao header: ${requestWithAuthorization.url}")
            return chain.proceed(requestWithAuthorization)
        }
        Log.w(TAG, "Access token não foi adicionado ao header: ${originalRequest.url}")
        return chain.proceed(originalRequest)
    }
}