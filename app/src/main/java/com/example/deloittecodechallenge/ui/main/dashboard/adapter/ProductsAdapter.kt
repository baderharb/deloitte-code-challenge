package com.example.deloittecodechallenge.ui.main.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.databinding.ItemProductBinding
import com.example.deloittecodechallenge.ui.main.dashboard.adapter.viewHolder.ProductsVH

class ProductsAdapter : RecyclerView.Adapter<ProductsVH>() {

    private var productsList = mutableListOf<Product>()
    private lateinit var binding: ItemProductBinding
    private var onItemClickListener: OnItemClickListener? = null

    fun setItems(items: List<Product>) {
        productsList.clear()
        productsList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsVH {
        binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsVH(binding)
    }

    override fun onBindViewHolder(holder: ProductsVH, position: Int) {
        holder.bind(productsList[position])

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(it, productsList[position], position)
        }

    }

    override fun getItemCount(): Int = productsList.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(view: View, item: Product, position: Int)
    }

    fun isFilterResultEmpty(): Boolean {
        return productsList.isEmpty()
    }
}