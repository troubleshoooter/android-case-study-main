package com.target.targetcasestudy.ui.deals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.usecase.DealsUseCase
import com.target.targetcasestudy.ui.adapter.uimodels.DealsUiModel
import com.target.targetcasestudy.ui.adapter.base.Visitable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(private val useCase: DealsUseCase) : ViewModel() {
    private val deals = MutableLiveData<List<Visitable>>()
    private val error = MutableLiveData<String>()
    private fun fetchDeals() {
        viewModelScope.launch {
            useCase.getAllDeals().let {
                if (it.isSuccess) {
                    deals.postValue(
                        it.getOrNull()?.map { product ->
                            DealsUiModel(
                                product
                            )
                        }
                    )
                } else {
                    error.value = it.exceptionOrNull()?.message
                }
            }
        }
    }

    fun getDeals(): LiveData<List<Visitable>> {
        if (deals.value.isNullOrEmpty()) {
            fetchDeals()
        }
        return deals
    }

    fun getErrorMessage(): LiveData<String> {
        return error
    }
}