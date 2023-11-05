package com.example.hstest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hstest.databinding.ItemAdBinding

class AdsAdapter(
    private val adsList: List<Ad>
) : ListAdapter<Ad, AdViewHolder>(AdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad = adsList[position]
        holder.bind(ad)
    }
    override fun getItemCount(): Int = adsList.size
}

class AdViewHolder(
    private val binding: ItemAdBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ad: Ad) {
        binding.apply {
            image.setImageResource(ad.image)
        }
    }
}

class AdDiffCallback : DiffUtil.ItemCallback<Ad>() {
    override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem == newItem
    }
}
