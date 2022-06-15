package com.harman.mvvmkmmsample.data.db

import com.harman.mvvmkmmsample.data.net.model.UserResponse
import com.harman.mvvmkmmsample.domain.model.User


fun List<UserResponse>.toDomainUsers(): List<User> = map {
    User(
        login = it.login,
        id = it.id.toString(),
        avatarUrl = it.avatar_url,
        url = it.url,
        htmlUrl = it.html_url
    )
}