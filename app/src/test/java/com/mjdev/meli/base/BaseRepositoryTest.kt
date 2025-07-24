package com.mjdev.meli.base

import android.util.Log
import com.mjdev.meli.data.remote.api.MeliApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

/**
 * Classe base para testes de repositórios.
 */
@ExperimentalCoroutinesApi
abstract class BaseRepositoryTest {

    /**
     * Dispatcher de teste usado para simular o Dispatchers.Main
     */
    protected val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    /**
     * Serviço de API simulado usado nos testes.
     */
    protected lateinit var mockApiService: MeliApiService

    /**
     * Configuração inicial do teste.
     * Define o dispatcher principal para o dispatcher de teste.
     */
    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockApiService = mockk()
        mockkStatic(Log::class)
        every { Log.e(any<String>(), any<String>()) } returns 0
        every { Log.e(any<String>(), any<String>(), any<Throwable>()) } returns 0
        every { Log.d(any<String>(), any<String>()) } returns 0
        every { Log.w(any<String>(), any<String>()) } returns 0
    }

    /**
     * Limpeza após os testes.
     * Reseta o dispatcher principal para evitar interferência em outros testes.
     */
    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        unmockkStatic(Log::class)
    }
}