package com.example.hstest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hstest.data.dto.DishDTO
import com.example.hstest.domain.model.Dish

@Entity
data class DishEntity(
    @PrimaryKey
    val idMeal: Int = 0,
    val strMeal: String?,
    val strDrinkAlternate: String?,
    var strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strTags: String?,
) {
    fun toDish() = Dish(
        idMeal,
        strMeal,
        strCategory,
        strInstructions,
        strMealThumb,
        strTags,
    )

    companion object {
        fun fromDto(dto: DishDTO) = DishEntity(
            dto.idMeal,
            dto.strMeal,
            dto.strDrinkAlternate,
            dto.strCategory,
            dto.strArea,
            dto.strInstructions,
            dto.strMealThumb,
            dto.strTags,
        )
    }
}

fun List<DishDTO>.toEntity(): List<DishEntity> = map(DishEntity.Companion::fromDto)