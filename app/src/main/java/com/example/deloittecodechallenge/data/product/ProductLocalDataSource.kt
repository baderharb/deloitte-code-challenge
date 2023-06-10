package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.data.Product

interface ProductLocalDataSource {

    suspend fun addProductsToLocalDB(products: List<Product>)

    suspend fun getProductsFromLocalDB(): List<Product>

}