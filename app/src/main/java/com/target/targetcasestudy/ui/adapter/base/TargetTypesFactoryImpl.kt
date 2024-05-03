package com.target.targetcasestudy.ui.adapter.base

import android.view.View
import com.target.targetcasestudy.data.source.remote.model.Product
import com.target.targetcasestudy.ui.adapter.DealsViewHolder
import com.target.targetcasestudy.ui.adapter.ProductDetailsCardViewHolder
import com.target.targetcasestudy.ui.adapter.ProductItemCardViewHolder

class TargetTypesFactoryImpl(
    val onDealItemClick: ((Product) -> Unit)? = null
) : TargetTypesFactory {
    override fun deals(data: Product): Int = DealsViewHolder.LAYOUT
    override fun empty(): Int = DefaultEmptyViewHolder.LAYOUT
    override fun productDetails(product: Product): Int = ProductDetailsCardViewHolder.LAYOUT

    override fun productItem(product: Product): Int = ProductItemCardViewHolder.LAYOUT

    override fun createViewHolder(view: View, type: Int): AbstractViewHolder<*> {
        return when (type) {
            DealsViewHolder.LAYOUT -> DealsViewHolder(view, onDealItemClick)
            ProductItemCardViewHolder.LAYOUT -> ProductItemCardViewHolder(view)
            ProductDetailsCardViewHolder.LAYOUT -> ProductDetailsCardViewHolder(view)
            else -> DefaultEmptyViewHolder(view)
        }
    }
}
