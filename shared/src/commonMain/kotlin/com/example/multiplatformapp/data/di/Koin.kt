package com.example.multiplatformapp.data.di

import com.example.multiplatformapp.data.remote.implementations.GithubApi
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration) =
    startKoin {
        appDeclaration()
        modules(
            apiModule
        )
    }

fun initKoin() = initKoin {}

private val apiModule = module {
    single {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
        }
    }
    factory { GithubApi(get()) }
}