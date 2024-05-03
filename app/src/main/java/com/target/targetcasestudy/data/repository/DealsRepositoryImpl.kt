package com.target.targetcasestudy.data.repository

import com.target.targetcasestudy.data.source.remote.RemoteDataSource
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.repository.DealsRepository
import javax.inject.Inject

class DealsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DealsRepository {
    override suspend fun getAllDeals(): List<Product> {
        // improvement leverage local source
        return remoteDataSource.getAllDeals()
    }

    override suspend fun getDeal(id: Int): Product {
        // improvement leverage local source

        return remoteDataSource.getDeal(id)
    }
}