package com.example.multiplatformapp.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubResponse(

    @SerialName("total_count"        ) var totalCount        : Int?                     = null,
    @SerialName("incomplete_results" ) var incompleteResults : Boolean?                 = null,
    @SerialName("items"              ) var items             : ArrayList<RepositoryDto> = arrayListOf()

)

@Serializable
data class RepositoryDto(

    @SerialName("id"            ) var id            : Int?       = null,
    @SerialName("name"          ) var name          : String?    = null,
    @SerialName("owner"         ) var owner         : OwnerDto?  = OwnerDto(),
    @SerialName("description"   ) var description   : String?    = null,
    @SerialName("updated_at"    ) var updatedAt     : String?    = null,
)

@Serializable
data class OwnerDto(

    @SerialName("login"     ) var login     : String?  = null,
    @SerialName("id"        ) var id        : Int?     = null,
    @SerialName("avatar_url") var avatarUrl : String?  = null,
)