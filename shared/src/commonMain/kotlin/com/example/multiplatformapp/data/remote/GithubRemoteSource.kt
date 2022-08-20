package com.example.multiplatformapp.data.remote

import com.example.multiplatformapp.data.remote.api.GithubApi

class GithubRemoteSource(private val githubApi: GithubApi) {

    suspend fun getGithubRepositories(query: String) = githubApi.getGithubRepositories(query)
}