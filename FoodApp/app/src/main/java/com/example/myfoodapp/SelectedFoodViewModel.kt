package com.example.myfoodapp

import androidx.lifecycle.ViewModel

class SelectedFoodViewModel : ViewModel() {
    private val selectedFoodList = mutableListOf<String>()

    // Phương thức để thêm món ăn đã chọn vào danh sách
    fun addSelectedFood(food: String) {
        selectedFoodList.add(food)
    }

    // Phương thức để lấy danh sách món ăn đã chọn
    fun getSelectedFoodList(): List<String> {
        return selectedFoodList
    }
}
