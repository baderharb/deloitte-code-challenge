package com.example.deloittecodechallenge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products_table")
data class ProductDB(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "updated") val updated: String?
)

data class Product(
    val id: Long?,
    val title: String?,
    val type: String?,
    val url: String?,
    val updated: String?
)