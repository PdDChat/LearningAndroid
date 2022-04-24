package com.learningandroid.model.repository

import com.learningandroid.model.service.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val gitHubService: GitHubService) {

    suspend fun searchLoginInfo(name: String) = gitHubService.searchLoginInfo(name)

    suspend fun searchRepositories(name: String) = gitHubService.searchRepositories(name)
}