package com.learningandroid.model.service

import com.learningandroid.model.data.Repositories
import com.learningandroid.model.data.LoginInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{userName}")
    suspend fun searchLoginInfo(@Path("userName") name: String) : Response<LoginInfo>

    @GET("/users/{userName}/repos")
    suspend fun searchRepositories(@Path("userName") name: String) : Response<List<Repositories>>
}