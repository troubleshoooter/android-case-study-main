package com.target.targetcasestudy.data.source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.target.targetcasestudy.domain.model.Product as DomainProduct

@JsonClass(generateAdapter = true)
data class Product(
    val id: Int,
    val title: String,
    val aisle: String,
    val availability: String,
    val fulfillment: String,
    @Json(name = "image_url")
    val imageUrl: String?,
    val description: String,
    @Json(name = "sale_price", ignore = true)
    val salePrice: Price? = null,
    @Json(name = "regular_price")
    val regularPrice: Price
)

fun Product.toDomain(): DomainProduct {
    return DomainProduct(
        id,
        title,
        aisle,
        availability,
        fulfillment,
        imageUrl,
        description,
        salePrice?.toDomain(),
        regularPrice.toDomain()
    )
}