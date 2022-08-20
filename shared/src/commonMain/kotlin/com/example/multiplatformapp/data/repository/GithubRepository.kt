package com.example.multiplatformapp.data.repository

import com.example.multiplatformapp.data.mapper.GithubMapper
import com.example.multiplatformapp.data.remote.api.GithubApi
import com.example.multiplatformapp.domain.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GithubRepository : KoinComponent {
    private val githubApi: GithubApi by inject()
    private val githubMapper: GithubMapper by inject()

    suspend fun getGithubRepositories(query: String): List<Repository> {
        val response = githubApi.getGithubRepositories(query = query)
        return githubMapper.mapGitRepositoriesResponseToListOfGitRepositories(response)
    }
}