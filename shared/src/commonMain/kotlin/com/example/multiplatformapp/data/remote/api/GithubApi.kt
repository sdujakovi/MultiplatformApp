package com.example.multiplatformapp.data.remote.api

import com.example.multiplatformapp.data.remote.responses.GithubResponse
import com.example.multiplatformapp.data.utils.Constants.BASE_URL
import com.example.multiplatformapp.data.utils.Constants.SEARCH
import com.example.multiplatformapp.data.utils.Constants.SORT_BY_UPDATE
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubApi(private val ktorApi: KtorApi) : KtorApi by ktorApi {

    suspend fun getGithubRepositories(query: String): GithubResponse =
        withContext(Dispatchers.Default) {
            client.get(
                URLBuilder(BASE_URL)
                    .apply {
                        path(SEARCH)
                        parameters.append("q", query)
                        parameters.append("sort", SORT_BY_UPDATE)
                    }
                    .build()
            ).body()
        }
}