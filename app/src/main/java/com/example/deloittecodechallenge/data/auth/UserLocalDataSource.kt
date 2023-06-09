package com.example.deloittecodechallenge.data.auth

interface UserLocalDataSource {

    suspend fun getUserLocal(): String

}