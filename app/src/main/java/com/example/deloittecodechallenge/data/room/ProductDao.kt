package com.example.deloittecodechallenge.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.deloittecodechallenge.data.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    suspend fun getAll(): List<Product>

    @Insert
    fun insertAll(products: List<Product>)

//    @Insert
//    suspend fun insertAll(vararg products: Product)

    @Delete
    suspend fun delete(product: Product)
}