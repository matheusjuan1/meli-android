package com.mjdev.meli.di

import com.mjdev.meli.AppConfig
import com.mjdev.meli.data.remote.api.MeliApiService
import com.mjdev.meli.data.remote.di.NetworkModule
import com.mjdev.meli.data.repository.MeliRepository
import com.mjdev.meli.data.repository.mock.MeliRepositoryMock
import com.mjdev.meli.domain.repository.IMeliRepository
import com.mjdev.meli.ui.screens.product_details.ProductDetailsViewModel
import com.mjdev.meli.ui.screens.products.ProductsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module para injetar as dependências da aplicação.
 */
val appModules = module {

    // Api Service
    single<MeliApiService> { NetworkModule.meliApiService }

    // Repository
    single<IMeliRepository> {
        if (AppConfig.USE_MOCKS) {
            MeliRepositoryMock(androidContext())
        } else {
            MeliRepository(get())
        }
    }

    // ViewModels
    viewModel { ProductsViewModel(get()) }
    viewModel { ProductDetailsViewModel(get()) }
}