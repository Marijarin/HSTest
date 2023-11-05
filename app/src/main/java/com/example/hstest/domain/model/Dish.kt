package com.example.hstest.domain.model

data class Dish(
    val idMeal : Int = 0,
    val strMeal : String?,
    var strCategory : String?,
    val strInstructions : String?,
    val strMealThumb : String?,
    val strTags : String?,
)