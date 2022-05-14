package com.learningandroid.di

import com.learningandroid.model.service.GitHubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideGitHubService(@Named("GitHubRetrofit") retrofit: Retrofit): GitHubService =
        retrofit.create(GitHubService::class.java)
}