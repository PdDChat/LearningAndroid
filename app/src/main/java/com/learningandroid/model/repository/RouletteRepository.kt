package com.learningandroid.model.repository

import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.data.RouletteInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RouletteRepository {
    suspend fun getRouletteInfo(): List<RouletteInfo>
    suspend fun registerRouletteInfo(name: String)
}

class RouletteRepositoryImpl @Inject constructor(private val dao: RouletteInfoDao) : RouletteRepository {

    override suspend fun getRouletteInfo(): List<RouletteInfo> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    override suspend fun registerRouletteInfo(name: String) = withContext(Dispatchers.IO) {
        val info = RouletteInfo().toRouletteInfo(name)
        dao.insert(info)
    }
}