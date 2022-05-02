package com.learningandroid.model.repository

import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.model.data.RouletteListInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface RouletteRepository {
    suspend fun getRouletteInfo(): Response<RouletteListInfo>
}

class RouletteRepositoryImpl @Inject constructor() : RouletteRepository {

    override suspend fun getRouletteInfo(): Response<RouletteListInfo> = withContext(Dispatchers.IO) {
        // TODO 一旦dummy。SharedPreferenceの値を取得するように修正
        Response.success(RouletteListInfo(
                rouletteInfo = listOf(
                    RouletteInfo(name = "test1"),
                    RouletteInfo(name = "test2")
                )
        ))
    }
}