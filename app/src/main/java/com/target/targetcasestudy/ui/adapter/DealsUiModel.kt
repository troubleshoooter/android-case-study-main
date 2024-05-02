package com.target.targetcasestudy.ui.adapter

import com.target.targetcasestudy.data.source.remote.model.Product
import com.target.targetcasestudy.ui.adapter.base.TargetTypesFactory
import com.target.targetcasestudy.ui.adapter.base.Visitable

data class DealsUiModel(val product: Product) : Visitable {
    override fun type(typeFactory: TargetTypesFactory): Int {
        return typeFactory.deals(product)
    }
}