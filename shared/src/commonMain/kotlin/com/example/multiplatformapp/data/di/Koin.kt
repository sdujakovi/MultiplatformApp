package com.example.multiplatformapp.data.di

import com.example.multiplatformapp.data.mapper.GithubMapper
import com.example.multiplatformapp.data.remote.GithubRemoteSource
import com.example.multiplatformapp.data.remote.api.GithubApi
import com.example.multiplatformapp.data.remote.api.KtorApi
import com.example.multiplatformapp.data.remote.api.KtorApiImpl
import com.example.multiplatformapp.data.repository.GithubRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration) =
    startKoin {
        appDeclaration()
        modules(
            apiModule,
            mapperModule
        )
    }

fun initKoin() = initKoin {}

private val apiModule = module {
    single<KtorApi> { KtorApiImpl() }
    factory { GithubApi(get()) }
}

private val mapperModule = module {
    single { GithubMapper() }
}

private val repositoryModule = module {
    factory { GithubRemoteSource(get()) }
    single { GithubRepository() }
}