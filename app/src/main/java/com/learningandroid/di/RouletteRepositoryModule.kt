package com.learningandroid.di

import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.model.repository.RouletteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RouletteRepositoryModule {

    @Singleton
    @Provides
    fun provideRouletteRepository(@Named("RouletteDaoModule") dao: RouletteInfoDao): RouletteRepository =
        RouletteRepositoryImpl(dao)
}