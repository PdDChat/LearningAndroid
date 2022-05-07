package com.learningandroid.model.repository

import com.learningandroid.model.data.LoginInfo
import com.learningandroid.model.data.Repositories
import com.learningandroid.model.service.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface GitHubRepository {
    suspend fun searchLoginInfo(name: String): Response<LoginInfo>
    suspend fun searchRepositories(name: String): Response<List<Repositories>>
}

class GitHubRepositoryImpl @Inject constructor(private val gitHubService: GitHubService) : GitHubRepository {

    override suspend fun searchLoginInfo(name: String) = withContext(Dispatchers.IO) {
        gitHubService.searchLoginInfo(name)
    }

    override suspend fun searchRepositories(name: String) = withContext(Dispatchers.IO) {
        gitHubService.searchRepositories(name)
    }
}