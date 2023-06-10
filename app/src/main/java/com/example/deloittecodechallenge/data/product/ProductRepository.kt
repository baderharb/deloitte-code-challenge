package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.data.models.ProductResponse
import com.example.deloittecodechallenge.data.remote.DefaultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(
    private val productRemoteDataSource: ProductRemoteDataSource,
//    private val productLocalDataSource: ProductLocalDataSource,
) : ProductDataSource {

//    override suspend fun addProductsToLocalDB(products: List<Product>) {
//        withContext(Dispatchers.IO) {
//            productLocalDataSource.addProductsToLocalDB(products)
//        }
//    }

//    override suspend fun getProductsFromLocalDB(): List<Product> {
//        return withContext(Dispatchers.IO) {
//            productLocalDataSource.getProductsFromLocalDB()
//        }
//    }

    override suspend fun fetchAllRemoteProducts(): DefaultResponse<ProductResponse> { // TODO :the return type should be changed to be providing a list of products from remote
        return withContext(Dispatchers.IO) {
            productRemoteDataSource.fetchAllRemoteProducts()
        }
    }

}