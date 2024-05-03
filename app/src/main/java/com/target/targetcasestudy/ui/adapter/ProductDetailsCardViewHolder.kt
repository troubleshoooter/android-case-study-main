package com.target.targetcasestudy.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.source.remote.model.Product
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder

class ProductDetailsCardViewHolder(itemView: View) :
    AbstractViewHolder<ProductDetailsCardUiModel>(itemView) {

    private val productDetailsDesc =
        itemView.findViewById<TextView>(R.id.product_details_description)


    companion object {
        @LayoutRes
        val LAYOUT = R.layout.product_details_card
    }

    override fun bind(model: ProductDetailsCardUiModel) {
        productDetailsDesc.text = model.product.description
    }
}