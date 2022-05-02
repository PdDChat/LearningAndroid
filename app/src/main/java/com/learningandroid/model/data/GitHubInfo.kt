package com.learningandroid.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginInfo(
    @Json(name = "login")
    val loginName: String = "",
    val id: Int = 0,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
): Parcelable

@Parcelize
data class Repositories(
    val id: Int = 0,
    val name: String = "",
    @Json(name = "full_name")
    val fullName: String = "",
): Parcelable
