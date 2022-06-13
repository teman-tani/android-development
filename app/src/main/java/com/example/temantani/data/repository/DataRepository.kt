package com.example.temantani.data.repository

import com.example.temantani.data.local.Data

class DataRepository {
    fun getRecommendation() = Data.getRecommendation()
    fun getMenuCategory() = Data.getMenuCategory()
}