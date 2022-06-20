package com.learningandroid.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class BookInfo(
    val items: List<Items> = listOf(),
)

@Parcelize
data class Items(
    val volumeInfo: VolumeInfo? = VolumeInfo(),
) : Parcelable

@Parcelize
data class VolumeInfo(
    val title: String = "",
    val imageLinks: ImageLinks? = ImageLinks(),
) : Parcelable

@Parcelize
data class ImageLinks(
    val thumbnail: String = "",
) : Parcelable
