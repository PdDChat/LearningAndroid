package com.learningandroid.di

import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.repository.GitHubRepository
import com.learningandroid.model.repository.GitHubRepositoryImpl
import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.model.repository.RouletteRepositoryImpl
import com.learningandroid.model.service.GitHubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGitHubRepository(gitHubService: GitHubService): GitHubRepository =
        GitHubRepositoryImpl(gitHubService)

    @Singleton
    @Provides
    fun provideRouletteRepository(@Named("RouletteDaoModule") dao: RouletteInfoDao): RouletteRepository =
        RouletteRepositoryImpl(dao)
}