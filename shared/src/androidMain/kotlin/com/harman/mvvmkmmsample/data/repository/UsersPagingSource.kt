package com.harman.mvvmkmmsample.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.harman.mvvmkmmsample.data.db.toDomainUsers
import com.harman.mvvmkmmsample.data.net.GitHubUsersApi
import com.harman.mvvmkmmsample.data.net.model.Response
import com.harman.mvvmkmmsample.domain.model.User
import io.ktor.client.*
import java.io.IOException


actual class UsersPagingSource(
    private val api: GitHubUsersApi, private val client: HttpClient, private val userName: String
) : PagingSource<Long, User>() {


    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, User> {
        val lastUserId = params.key
        return try {
            val response = api.getUsers(
                client, userName,
                "since", userId = lastUserId,
            )
            var userList =
                if (response is Response.Success) {
                    response.data
                } else {
                    null
                }
            val nextKey =
                if (userList?.isEmpty() == true) {
                    null
                } else {
                    userList?.last()?.id
                }
            println("userList $userList")
            LoadResult.Page(
                data = userList?.toDomainUsers() ?: listOf<User>(),
                prevKey = lastUserId,
                nextKey = nextKey
            )
        } catch (ioExp: IOException) {
            LoadResult.Error(ioExp)
        } catch (exp: Exception) {
            LoadResult.Error(exp)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Long, User>): Long? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}