package com.learningandroid.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubInfo(
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "full_name")
    val fullName: String = "",
): Parcelable
