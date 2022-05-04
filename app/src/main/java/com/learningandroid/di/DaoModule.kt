package com.learningandroid.di

import com.learningandroid.db.AppDatabase
import com.learningandroid.model.dao.RouletteInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    @Named("RouletteDaoModule")
    fun provideRouletteInfoDao(database: AppDatabase): RouletteInfoDao =
        database.rouletteInfoDao()
}