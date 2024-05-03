package com.target.targetcasestudy.ui.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.domain.usecase.DealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListComposeViewModel @Inject constructor(private val useCase: DealsUseCase) :
    ViewModel() {

    private val deals = MutableStateFlow<List<Product>?>(null)
    private val error = MutableStateFlow<String?>(null)

    private fun fetchDeals() {
        viewModelScope.launch {
            useCase.getAllDeals().let { result ->
                if (result.isSuccess) {
                    deals.update {
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

    fun getDeals(): StateFlow<List<Product>?> {
        if (deals.value.isNullOrEmpty()) {
            fetchDeals()
        }

        return deals.asStateFlow()
    }

    fun getErrorMessage(): StateFlow<String?> {
        return error.asStateFlow()
    }
}