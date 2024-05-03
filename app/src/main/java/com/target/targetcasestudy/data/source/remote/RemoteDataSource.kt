package com.target.targetcasestudy.data.source.remote

import com.target.targetcasestudy.data.source.remote.api.DealApiKtx
import com.target.targetcasestudy.data.source.remote.model.toDomain
import com.target.targetcasestudy.domain.model.Product
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiKtx: DealApiKtx) {
    suspend fun getAllDeals(): List<Product> {
        return apiKtx.retrieveDeals().deals.map {
            it.toDomain()
        }
    }

    suspend fun getDeal(id: Int): Product {
        return apiKtx.retrieveDeal(id).toDomain()
    }
}