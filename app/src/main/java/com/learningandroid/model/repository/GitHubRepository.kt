package com.learningandroid.model.repository

import com.learningandroid.model.service.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val gitHubService: GitHubService) {

    suspend fun searchUserRepositories(name: String) = gitHubService.searchUserRepositories(name)
}