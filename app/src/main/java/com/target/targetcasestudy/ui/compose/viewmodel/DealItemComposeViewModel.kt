package com.target.targetcasestudy.ui.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.usecase.DealsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DealItemComposeViewModel @Inject constructor(private val useCase: DealsUseCase) :
    ViewModel() {

    private val deal = MutableStateFlow<Product?>(null)
    private val error = MutableStateFlow<String?>(null)

    private fun fetchDealInfo(id: Int) {
        viewModelScope.launch {
            useCase.getDeal(id).let { result ->
                if (result.isSuccess) {
                    deal.update {
                        result.getOrNull()
                    }
                } else {
                    error.update {
                        result.exceptionOrNull()?.message
                    }
                }
            }
        }
    }

    fun getDealInfo(id: Int): StateFlow<Product?> {
        if (deal.value == null) {
            fetchDealInfo(id)
        }

        return deal.asStateFlow()
    }

    fun getErrorMessage(): StateFlow<String?> {
        return error.asStateFlow()
    }
}