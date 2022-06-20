package com.learningandroid.model.repository

import com.learningandroid.model.data.BookInfo
import com.learningandroid.model.service.SearchBookService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface BookRepository {
    suspend fun searchBookInfo(query: String): Response<BookInfo>
}

class BookRepositoryImpl @Inject constructor(private val searchBookService: SearchBookService) : BookRepository {

    override suspend fun searchBookInfo(query: String) = withContext(Dispatchers.IO) {
        searchBookService.searchBookInfo(query)
    }
}