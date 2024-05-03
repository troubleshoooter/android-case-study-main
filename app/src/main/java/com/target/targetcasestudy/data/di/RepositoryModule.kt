package com.target.targetcasestudy.data.di

import com.target.targetcasestudy.data.repository.DealsRepositoryImpl
import com.target.targetcasestudy.domain.repository.DealsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDealsRepository(
        dealsRepositoryImpl: DealsRepositoryImpl
    ): DealsRepository
}