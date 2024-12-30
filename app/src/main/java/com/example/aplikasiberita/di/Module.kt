package com.example.aplikasiberita.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.aplikasiberita.MainViewModel
import com.example.aplikasiberita.data.datasource.remote.api.service.ApiService
import com.example.aplikasiberita.data.repository.NewsRepository
import com.example.aplikasiberita.data.repository.NewsRepositoryImpl
import com.example.aplikasiberita.data.utils.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val vmModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { NewsRepositoryImpl(get()) } bind NewsRepository::class
}

val apiModule = module {
    single<Retrofit> {
        return@single Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<ChuckerInterceptor>())
        }.build()
    }

    single {
        ChuckerInterceptor.Builder(androidApplication())
            .collector(ChuckerCollector(androidApplication()))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}