package com.learningandroid.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roulette_info")
data class RouletteInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    @ColumnInfo(name = "selected_num")
    val selectedNum: Int = 0
) {

    fun toRouletteInfo(name: String): RouletteInfo {
        return RouletteInfo(id , name, selectedNum)
    }
}
