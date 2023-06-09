package com.example.deloittecodechallenge.data.auth

import com.example.deloittecodechallenge.data.remote.ApiEndpoints
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSourceImpl @Inject constructor(
    private val apiEndPoints: ApiEndpoints,
) : UserRemoteDataSource {
    override suspend fun getUserRemote(): String {
        return "bder Remote"
    }
}