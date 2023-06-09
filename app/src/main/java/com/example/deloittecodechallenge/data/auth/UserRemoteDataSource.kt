package com.example.deloittecodechallenge.data.auth

interface UserRemoteDataSource {

    suspend fun getUserRemote(): String

}