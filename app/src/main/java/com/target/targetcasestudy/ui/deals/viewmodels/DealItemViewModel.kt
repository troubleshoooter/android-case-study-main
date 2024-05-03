package com.target.targetcasestudy.ui.deals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.usecase.DealsUseCase
import com.target.targetcasestudy.ui.adapter.uimodels.ProductDetailsCardUiModel
import com.target.targetcasestudy.ui.adapter.uimodels.ProductItemCardUiModel
import com.target.targetcasestudy.ui.adapter.base.Visitable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealItemViewModel @Inject constructor(private val useCase: DealsUseCase) : ViewModel() {
    private val deal = MutableLiveData<List<Visitable>>()
    private val error = MutableLiveData<String>()
    private fun fetchDealInfo(id: Int) {
        viewModelScope.launch {
            useCase.getDeal(id).let {
                if (it.isSuccess) {
                    val list = listOf(
                        ProductItemCardUiModel(it.getOrNull()),
                        ProductDetailsCardUiModel(it.getOrNull())
                    )
                    deal.postValue(list)
                } else {
                    error.value = it.exceptionOrNull()?.message
                }
            }
        }
    }

    fun getDealInfo(id: Int): LiveData<List<Visitable>> {
        if (deal.value.isNullOrEmpty()) {
            fetchDealInfo(id)
        }
        return deal
    }

    fun getErrorMessage(): LiveData<String> {
        return error
    }
}