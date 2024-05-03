package com.target.targetcasestudy.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder
import com.target.targetcasestudy.ui.adapter.uimodels.ProductItemCardUiModel

class ProductItemCardViewHolder(itemView: View) :
    AbstractViewHolder<ProductItemCardUiModel>(itemView) {

    private val productImageView =
        itemView.findViewById<ImageFilterView>(R.id.deal_item_image)
    private val productSalePrice =
        itemView.findViewById<TextView>(R.id.deal_item_sale_price)
    private val productRegPrice =
        itemView.findViewById<TextView>(R.id.deal_item_reg_price)
    private val productTitle = itemView.findViewById<TextView>(R.id.deal_item_title)
    private val productChannel =
        itemView.findViewById<TextView>(R.id.deal_item_channel)
  

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.product_item_card
    }

    override fun bind(model: ProductItemCardUiModel) {
        model.product?.run {
            Glide.with(itemView)
                .load(imageUrl)
                .into(productImageView)
            if (salePrice != null) {
                productSalePrice.text = salePrice.displayString
                productRegPrice.text = regularPrice.displayString
            } else {
                productSalePrice.text = regularPrice.displayString
            }
            productChannel.text = fulfillment
            productTitle.text = title
        }
    }
}