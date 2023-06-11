package com.example.deloittecodechallenge.ui.main.dashboard.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deloittecodechallenge.R
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.databinding.ItemProductBinding

class ProductsVH(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        Glide.with(binding.root.context).load(product.url).error(R.drawable.baseline_image_24)
            .placeholder(R.drawable.baseline_image_24).into(binding.productImg)
        binding.product = product
    }

}