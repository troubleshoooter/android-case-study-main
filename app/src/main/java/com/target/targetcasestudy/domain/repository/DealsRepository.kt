package com.target.targetcasestudy.domain.repository

import com.target.targetcasestudy.domain.model.Product

interface DealsRepository {
    suspend fun getAllDeals(): List<Product>
    suspend fun getDeal(id: Int): Product
}