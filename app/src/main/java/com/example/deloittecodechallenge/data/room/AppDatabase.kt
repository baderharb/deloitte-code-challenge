package com.example.deloittecodechallenge.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deloittecodechallenge.data.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}