package com.learningandroid.di

import com.learningandroid.model.repository.GitHubRepository
import com.learningandroid.model.repository.GitHubRepositoryImpl
import com.learningandroid.model.service.GitHubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubRepositoryModule {

    @Singleton
    @Provides
    fun provideGitHubRepository(gitHubService: GitHubService): GitHubRepository =
        GitHubRepositoryImpl(gitHubService)
}