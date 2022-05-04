package com.learningandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.data.RouletteInfo

@Database(entities = [RouletteInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rouletteInfoDao(): RouletteInfoDao
}