package com.learningandroid.model.repository

import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.ui.roulette.response.DeleteAllStatus
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RouletteRepository {
    suspend fun getRouletteInfo(): List<RouletteInfo>
    suspend fun registerRouletteInfo(name: String): RegisterStatus
    suspend fun deleteRouletteInfo(name: String): DeleteStatus
    suspend fun deleteAllRouletteInfo(): DeleteAllStatus
}

class RouletteRepositoryImpl @Inject constructor(
    private val dao: RouletteInfoDao,
) : RouletteRepository {

    override suspend fun getRouletteInfo(): List<RouletteInfo> {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }

    override suspend fun registerRouletteInfo(name: String): RegisterStatus {
        return withContext(Dispatchers.IO) {
            val info = RouletteInfo().toRouletteInfo(name)
            try {
                dao.insert(info)
                RegisterStatus.Success
            } catch (e :Exception) {
                RegisterStatus.Error
            }
        }
    }

    override suspend fun deleteRouletteInfo(name: String): DeleteStatus {
        return withContext(Dispatchers.IO) {
            val result = dao.deleteCell(name)
            if (result == 1) {
                DeleteStatus.Success
            } else {
                DeleteStatus.Error
            }
        }
    }

    override suspend fun deleteAllRouletteInfo(): DeleteAllStatus {
        return withContext(Dispatchers.IO) {
            try {
                val targetList = dao.getAll()
                dao.delete(targetList)
                DeleteAllStatus.Success
            } catch (e: Exception) {
                DeleteAllStatus.Failed
            }
        }
    }
}