package com.learningandroid.model.repository

import com.learningandroid.model.data.GithubInfo
import com.learningandroid.model.service.GithubService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepository {

    private val baseUrl: String = "https://api.github.com"

    suspend fun searchUserRepositories(name: String): Response<List<GithubInfo>> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()

        val service = retrofit.create(GithubService::class.java)
        return service.searchUserRepositories(name)
    }
}