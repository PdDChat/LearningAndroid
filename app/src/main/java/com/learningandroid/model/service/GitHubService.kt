package com.learningandroid.model.service

import com.learningandroid.model.data.GithubInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{userName}/repos")
    suspend fun searchUserRepositories(@Path("userName") name: String) : Response<List<GithubInfo>>
}