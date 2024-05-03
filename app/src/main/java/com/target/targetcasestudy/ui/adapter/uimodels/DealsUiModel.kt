package com.target.targetcasestudy.ui.adapter.uimodels

import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.ui.adapter.base.TargetTypesFactory
import com.target.targetcasestudy.ui.adapter.base.Visitable

data class DealsUiModel(val product: Product?) : Visitable {
    override fun type(typeFactory: TargetTypesFactory): Int {
        return typeFactory.deals(product)
    }
}