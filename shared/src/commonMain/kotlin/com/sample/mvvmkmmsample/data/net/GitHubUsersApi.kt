package com.sample.mvvmkmmsample.data.net

import com.sample.mvvmkmmsample.data.net.model.Response
import com.sample.mvvmkmmsample.data.net.model.UserResponse
import com.sample.mvvmkmmsample.exception.NetworkConnectionException
import com.sample.mvvmkmmsample.isNetworkAvailable
import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class GitHubUsersApi {

    suspend fun getUsers(
        client: HttpClient,
        username: String,
        since: String,
        userId: Long?
    ): Response<List<UserResponse>> {
        return try {
            if (isNetworkAvailable()) {
                Logger.DEFAULT.log("getGitHubRepoList - ")
                val url = "https://api.github.com/users"
                val response = client.get<List<UserResponse>>(url) {
                    parameter(since, userId)
                }
                Logger.DEFAULT.log("getGitHubRepoList - $response")
                Response.Success(response)
            } else {
                Response.Error(NetworkConnectionException())
            }
        } catch (ex: Exception) {
            Logger.DEFAULT.log("getGitHubRepoList - " + ex.message!!)
            Response.Error(ex)
        }
    }
}