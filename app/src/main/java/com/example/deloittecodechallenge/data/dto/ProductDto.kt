package com.example.deloittecodechallenge.data.dto

import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.data.models.ProductResponse.RemoteProduct

fun RemoteProduct.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        type = type,
        url = media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url ?: "",
        updated = updated,
    )
}