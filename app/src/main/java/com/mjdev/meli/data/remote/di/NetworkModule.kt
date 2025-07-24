package com.mjdev.meli.data.remote.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mjdev.meli.data.remote.api.MeliApiService
import com.mjdev.meli.data.remote.interceptors.AuthInterceptor
import com.mjdev.meli.data.remote.util.AppJson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * NetworkModule é responsável por fornecer as instâncias necessárias para a comunicação com a API do Mercado Livre.
 */
object NetworkModule {

    private const val BASE_URL = "https://api.mercadolibre.com/"

    /**
     * Instância do OkHttpClient configurada com interceptors e timeouts.
     */
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Instância do Retrofit configurada com o OkHttpClient e o conversor JSON.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /**
     * Serviço da API do Mercado Livre.
     */
    val meliApiService: MeliApiService by lazy {
        retrofit.create(MeliApiService::class.java)
    }
}