package com.example.multiplatformapp.data.mapper

import com.example.multiplatformapp.data.remote.responses.GithubResponse
import com.example.multiplatformapp.data.remote.responses.OwnerDto
import com.example.multiplatformapp.domain.Owner
import com.example.multiplatformapp.domain.Repository

class GithubMapper {
    fun mapGitRepositoriesResponseToListOfGitRepositories(response: GithubResponse): List<Repository> =
        response.items.map {
            Repository().apply {
                id = it.id.toString()
                name = it.name
                owner = mapOwner(it.owner)
                description = it.description
                lastUpdate = it.updatedAt
            }
        }

    private fun mapOwner(gitRepositoryOwner: OwnerDto?) = Owner().apply {
        id = gitRepositoryOwner?.id.toString()
        login = gitRepositoryOwner?.login
        avatarUrl = gitRepositoryOwner?.avatarUrl
    }
}