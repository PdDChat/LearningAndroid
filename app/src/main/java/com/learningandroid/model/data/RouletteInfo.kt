package com.learningandroid.model.data

data class RouletteListInfo(
    val rouletteInfo: List<RouletteInfo>
)

data class RouletteInfo(
    val name: String = "",
    val selectedNum: Int = 0
)
