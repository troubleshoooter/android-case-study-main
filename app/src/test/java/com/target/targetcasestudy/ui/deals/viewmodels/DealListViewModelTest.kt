package com.target.targetcasestudy.ui.deals.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.target.targetcasestudy.CoroutineTestDispatcherProvider
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.usecase.DealsUseCase
import com.target.targetcasestudy.ui.adapter.uimodels.DealsUiModel
import com.target.targetcasestudy.ui.adapter.uimodels.ProductDetailsCardUiModel
import com.target.targetcasestudy.ui.adapter.uimodels.ProductItemCardUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DealListViewModelTest {

    private val dispatchers = CoroutineTestDispatcherProvider

    private var useCase = mockk<DealsUseCase>()

    private lateinit var viewModel: DealListViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers.testDispatcher)
        viewModel = DealListViewModel(useCase)
    }


    @Test
    fun `getDeals fetches data and populates deals LiveData when successful`() {
        val viewModel = DealListViewModel(useCase)
        val mockDeal = mockk<Product>() // Assuming Product is the type retrieved from useCase
        val expectedDeals = listOf(DealsUiModel(mockDeal))

        // Mock successful use case response
        coEvery { useCase.getAllDeals() } returns Result.success(listOf(mockDeal))

        // Trigger data fetch
        viewModel.getDeals()

        // Verify LiveData updates
        val observedDeals = viewModel.getDeals().value
        val observedError = viewModel.getErrorMessage().value

        assertNotNull(observedDeals)
        assertEquals(expectedDeals, observedDeals)
        assertNull(observedError)
    }

    @Test
    fun `fetchDealInfo populates error LiveData when use case fails`() {
        val errorMessage = "Network Error"

        // Mock failed use case response
        val exception = Exception(errorMessage)
        coEvery { useCase.getAllDeals() } returns Result.failure(exception)

        // Trigger data fetch
        viewModel.getDeals()

        // Verify LiveData updates
        val observedDeal = viewModel.getDeals().value
        val observedError = viewModel.getErrorMessage().value

        assertNull(observedDeal)
        assertNotNull(observedError)
        assertEquals(errorMessage, observedError)
    }
    @Test
    fun `getDeals populates error LiveData when use case fails`() {
        val viewModel = DealListViewModel(useCase)
        val errorMessage = "Network Error"

        // Mock failed use case response
        val exception = Exception(errorMessage)
        coEvery { useCase.getAllDeals() } returns Result.failure(exception)

        // Trigger data fetch
        viewModel.getDeals()

        // Verify LiveData updates
        val observedDeals = viewModel.getDeals().value
        val observedError = viewModel.getErrorMessage().value

        assertNull(observedDeals)
        assertNotNull(observedError)
        assertEquals(errorMessage, observedError)
    }

}