package com.target.targetcasestudy.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.source.remote.api.DealApiKtx
import com.target.targetcasestudy.ui.adapter.DealsUiModel
import com.target.targetcasestudy.ui.adapter.base.Visitable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(private val apiKtx: DealApiKtx) : ViewModel() {
    val dealsLiveData = MutableLiveData<List<Visitable>>()
    fun getDeals() {
        CoroutineScope(Dispatchers.IO).launch {
            dealsLiveData.postValue(apiKtx.retrieveDeals().deals.map {
                DealsUiModel(it)
            })
        }
    }
}