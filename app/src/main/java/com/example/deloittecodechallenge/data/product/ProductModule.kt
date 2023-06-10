package com.example.deloittecodechallenge.data.product

import com.example.deloittecodechallenge.data.remote.ApiEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

//    @Provides
//    @Singleton
//    fun provideProductLocalDataSource(productDao: ProductDao): ProductLocalDataSource {
//        return ProductLocalDataSourceImpl(productDao)
//    }

    @Provides
    @Singleton
    fun provideProductRemoteDataSource(apiEndPoints: ApiEndpoints): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(apiEndPoints)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productRemoteDataSource: ProductRemoteDataSource,
//        productLocalDataSource: ProductLocalDataSource,
    ): ProductDataSource {
        return ProductRepository(productRemoteDataSource/*, productLocalDataSource*/)
    }

}