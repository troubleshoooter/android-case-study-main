package com.target.targetcasestudy.ui.deals.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.target.targetcasestudy.CoroutineTestDispatcherProvider
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.usecase.DealsUseCase
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
class DealItemViewModelTest {

    private val dispatchers = CoroutineTestDispatcherProvider

    private var useCase = mockk<DealsUseCase>()

    private lateinit var viewModel: DealItemViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers.testDispatcher)
        viewModel = DealItemViewModel(useCase)
    }


    @Test
    fun `fetchDealInfo fetches data and populates deal LiveData when successful`() {
        val mockDeal = mockk<Product>()

        // Mock successful use case response
        coEvery { useCase.getDeal(1) } returns Result.success(mockDeal)

        // Trigger data fetch
        viewModel.getDealInfo(1)

        // Verify LiveData updates

        val observedDeal = viewModel.getDealInfo(1).value
        val observedError = viewModel.getErrorMessage().value

        assertNotNull(viewModel.getDealInfo(1))
        assertEquals(2, observedDeal?.size)
        assertTrue(observedDeal?.first() is ProductItemCardUiModel)
        assertTrue(observedDeal?.get(1) is ProductDetailsCardUiModel)
        assertNull(observedError)
    }

    @Test
    fun `fetchDealInfo populates error LiveData when use case fails`() {
        val errorMessage = "Network Error"

        // Mock failed use case response
        val exception = Exception(errorMessage)
        coEvery { useCase.getDeal(1) } returns Result.failure(exception)

        // Trigger data fetch
        viewModel.getDealInfo(1)

        // Verify LiveData updates
        val observedDeal = viewModel.getDealInfo(1).value
        val observedError = viewModel.getErrorMessage().value

        assertNull(observedDeal)
        assertNotNull(observedError)
        assertEquals(errorMessage, observedError)
    }

}