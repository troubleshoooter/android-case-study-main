package com.target.targetcasestudy.domain.model

data class Product(
    val id: Int,
    val title: String,
    val aisle: String,
    val availability: String,
    val fulfillment: String,
    val imageUrl: String?,
    val description: String,
    val salePrice: Price? = null,
    val regularPrice: Price
)