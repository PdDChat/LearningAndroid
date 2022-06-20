package com.learningandroid.model.service

import com.learningandroid.model.data.BookInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBookService {

    @GET("/books/v1/volumes")
    suspend fun searchBookInfo(@Query("q") query: String): Response<BookInfo>
}