package com.example.deloittecodechallenge.data.remote

import com.example.deloittecodechallenge.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {

    @GET("svc/mostpopular/v2/viewed/30.json")
    suspend fun fetchProducts(
        @Query("api-key") apikey: String
    ): Response<ProductResponse>

}