package com.learningandroid.model.dao

import androidx.room.*
import com.learningandroid.model.data.RouletteInfo

@Dao
interface RouletteInfoDao {
    @Insert
    fun insert(rouletteInfo: RouletteInfo)

    @Update
    fun update(rouletteInfo: RouletteInfo)

    @Delete
    fun delete(rouletteInfo: RouletteInfo)

    @Query("select * from roulette_info")
    fun getAll(): List<RouletteInfo>

    @Query("delete from roulette_info where name = :deleteName")
    fun deleteCell(deleteName: String)
}