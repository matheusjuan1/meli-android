package com.mjdev.meli.base

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
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
import org.junit.Rule

/**
 * Classe base para testes de ViewModel.
 */
@ExperimentalCoroutinesApi
abstract class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    /**
     * Configuração inicial do teste.
     * Define o dispatcher principal para o dispatcher de teste e mocka os métodos de log.
     */
    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatcher)


        mockkStatic(Log::class)
        every { Log.e(any<String>(), any<String>()) } returns 0
        every { Log.e(any<String>(), any<String>(), any<Throwable>()) } returns 0
        every { Log.w(any<String>(), any<String>()) } returns 0
        every { Log.d(any<String>(), any<String>()) } returns 0
        every { Log.i(any<String>(), any<String>()) } returns 0
        every { Log.v(any<String>(), any<String>()) } returns 0
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