package com.example.hstest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hstest.R
import com.example.hstest.databinding.ItemDishBinding
import com.example.hstest.domain.model.Dish

class DishesAdapter() : ListAdapter<Dish, DishViewHolder>(DishDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding =
            ItemDishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
    }
}

class DishViewHolder(
    private val binding: ItemDishBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dish: Dish) {
        binding.apply {
            dishName.text = dish.strMeal
            dishDesc.text = dish.strInstructions?.substring(0..80) ?: "no text present"
            price.text = itemView.context.getString(R.string.price, "345")
            Glide.with(dishImage)
                .load(dish.strMealThumb)
                .error(R.drawable.twotone_error_outline_24)
                .into(dishImage)
        }
    }
}
class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem == newItem
    }
}
