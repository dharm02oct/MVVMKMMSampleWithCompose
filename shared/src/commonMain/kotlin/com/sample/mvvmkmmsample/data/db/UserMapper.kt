package com.sample.mvvmkmmsample.data.db

import com.sample.mvvmkmmsample.data.net.model.UserResponse
import com.sample.mvvmkmmsample.domain.model.User


fun List<UserResponse>.toDomainUsers(): List<User> = map {
    User(
        login = it.login,
        id = it.id.toString(),
        avatarUrl = it.avatar_url,
        url = it.url,
        htmlUrl = it.html_url
    )
}