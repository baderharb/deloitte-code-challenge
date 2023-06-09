package com.example.deloittecodechallenge.data.auth

import com.example.deloittecodechallenge.data.remote.ApiEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserLocalDataSource(): UserLocalDataSource {
        return UserLocalDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(apiEndPoints: ApiEndpoints): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(apiEndPoints)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource,
    ): UserDataSource {
        return UserRepository(userRemoteDataSource, userLocalDataSource)
    }

}