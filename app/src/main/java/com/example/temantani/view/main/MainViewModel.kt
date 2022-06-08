package com.example.temantani.view.main

import androidx.lifecycle.ViewModel
import com.example.temantani.data.repository.DataRepository

class MainViewModel: ViewModel() {
    private val repository = DataRepository()
    fun getMenuCategory() = repository.getMenuCategory()
}