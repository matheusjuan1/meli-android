package com.mjdev.meli.di

import com.mjdev.meli.AppConfig
import com.mjdev.meli.data.remote.api.MeliApiService
import com.mjdev.meli.data.remote.di.NetworkModule
import com.mjdev.meli.data.repository.MeliRepository
import com.mjdev.meli.data.repository.mock.MeliRepositoryMock
import com.mjdev.meli.domain.repository.IMeliRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    // Api Service
    single<MeliApiService> { NetworkModule.meliApiService }

    // Repository
    single<IMeliRepository> {
        if (AppConfig.useMocks) {
            MeliRepositoryMock(androidContext())
        } else {
            MeliRepository(get())
        }
    }

    // ViewModels
//    viewModel { ProductsViewModel(get()) }
}