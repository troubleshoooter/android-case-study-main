package com.target.targetcasestudy.domain.usecase

import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.repository.DealsRepository
import com.target.targetcasestudy.util.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DealsUseCase @Inject constructor(
    private val dealsRepository: DealsRepository,
    private val dispatchers: CoroutineDispatchers
) {

    suspend fun getAllDeals(): Result<List<Product>> {
        return withContext(dispatchers.io) {
            try {
                Result.success(dealsRepository.getAllDeals())
            } catch (e: HttpException) {
                Result.failure(RuntimeException("${e.code()} : ${e.message()}"))
            } catch (e: Exception) {
                Result.failure(RuntimeException("Something Went Wrong!"))
            }
        }
    }

    suspend fun getDeal(id: Int): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(dealsRepository.getDeal(id))
            } catch (e: HttpException) {
                Result.failure(RuntimeException("${e.code()} : ${e.message()}"))
            } catch (e: Exception) {
                Result.failure(RuntimeException("Something Went Wrong!"))
            }
        }
    }
}