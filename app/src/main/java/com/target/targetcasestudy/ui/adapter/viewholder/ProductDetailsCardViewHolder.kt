package com.target.targetcasestudy.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder
import com.target.targetcasestudy.ui.adapter.uimodels.ProductDetailsCardUiModel

class ProductDetailsCardViewHolder(itemView: View) :
    AbstractViewHolder<ProductDetailsCardUiModel>(itemView) {

    private val productDetailsDesc =
        itemView.findViewById<TextView>(R.id.product_details_description)


    companion object {
        @LayoutRes
        val LAYOUT = R.layout.product_details_card
    }

    override fun bind(model: ProductDetailsCardUiModel) {
        productDetailsDesc.text = model.product?.description
    }
}