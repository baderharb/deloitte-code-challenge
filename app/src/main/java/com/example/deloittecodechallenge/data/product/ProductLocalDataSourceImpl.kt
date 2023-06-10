package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.data.room.ProductDao
import javax.inject.Inject

//@Singleton
class ProductLocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDataSource {
    override suspend fun addProductsToLocalDB(products: List<Product>) {
        productDao.insertAll(products)
    }

    override suspend fun getProductsFromLocalDB(): List<Product> {
        return productDao.getAll()
    }

}