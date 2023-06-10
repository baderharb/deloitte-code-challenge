package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.BuildConfig
import com.example.deloittecodechallenge.data.models.ProductResponse
import com.example.deloittecodechallenge.data.remote.ApiEndpoints
import com.example.deloittecodechallenge.data.remote.DefaultResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRemoteDataSourceImpl @Inject constructor(
    private val apiEndPoints: ApiEndpoints
) : ProductRemoteDataSource {

    override suspend fun fetchAllRemoteProducts(): DefaultResponse<ProductResponse> {
        return try {
            DefaultResponse.create(apiEndPoints.fetchProducts(BuildConfig.API_KEY))
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }

}