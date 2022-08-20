package com.example.multiplatformapp.data.remote.implementations

import com.example.multiplatformapp.data.remote.responses.GithubResponse
import com.example.multiplatformapp.data.utils.Constants.BASE_URL
import com.example.multiplatformapp.data.utils.Constants.SEARCH
import com.example.multiplatformapp.data.utils.Constants.SORT_BY_UPDATE
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubApi constructor(private val httpClient: HttpClient) {

    suspend fun getGitRepositories(query: String): GithubResponse =
        withContext(Dispatchers.Default) {

            httpClient.get(
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