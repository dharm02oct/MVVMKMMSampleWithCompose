package com.harman.mvvmkmmsample.data.net.model

import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val url: String,
    val html_url:String,
)



