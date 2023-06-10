package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.data.models.ProductResponse
import com.example.deloittecodechallenge.data.remote.DefaultResponse

interface ProductRemoteDataSource {

    suspend fun fetchAllRemoteProducts(): DefaultResponse<ProductResponse>

}