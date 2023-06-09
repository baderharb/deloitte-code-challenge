package com.example.deloittecodechallenge.data.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : UserDataSource {
    override suspend fun getUserLocal(): String {
        return withContext(Dispatchers.IO) {
            userLocalDataSource.getUserLocal()
        }
    }

    override suspend fun getUserRemote(): String {
        return withContext(Dispatchers.IO) {
            userRemoteDataSource.getUserRemote()
        }
    }

}