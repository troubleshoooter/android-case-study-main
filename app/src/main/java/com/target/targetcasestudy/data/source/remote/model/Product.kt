package com.target.targetcasestudy.data.source.remote.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
