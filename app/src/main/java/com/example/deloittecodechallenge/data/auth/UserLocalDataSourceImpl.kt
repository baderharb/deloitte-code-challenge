package com.example.deloittecodechallenge.data.auth

class UserLocalDataSourceImpl : UserLocalDataSource {
    override suspend fun getUserLocal(): String {
        return "bder Local"
    }
}