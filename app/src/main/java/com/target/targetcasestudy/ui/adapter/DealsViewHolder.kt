package com.target.targetcasestudy.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder

class DealsViewHolder(itemView: View) : AbstractViewHolder<DealsUiModel>(itemView) {

    private val dealsImageView =
        itemView.findViewById<ImageFilterView>(R.id.deal_list_item_image_view)
    private val dealsSalePrice =
        itemView.findViewById<TextView>(R.id.deal_list_item_sale_price)
    private val dealsRegPrice =
        itemView.findViewById<TextView>(R.id.deal_list_item_reg_price)
    private val dealsTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    private val dealsChannel =
        itemView.findViewById<TextView>(R.id.deal_list_item_channel)
    private val dealsAvailability =
        itemView.findViewById<TextView>(R.id.deal_list_item_availability)
    private val greenColor = itemView.context.getColor(R.color.colorAccent)
    private val greyColor = itemView.context.getColor(R.color.gray_color)

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.deal_list_item
    }

    override fun bind(model: DealsUiModel) {
        with(model.product) {
            Glide.with(itemView)
                .load(imageUrl)
                .into(dealsImageView)
            if (salePrice != null) {
                dealsSalePrice.text = salePrice.displayString
                dealsRegPrice.text = regularPrice.displayString
            } else {
                dealsSalePrice.text = regularPrice.displayString
            }
            dealsChannel.text = fulfillment
            dealsTitle.text = title
            dealsAvailability.text = buildSpannedString {
                color(greenColor) {
                    append(availability)
                }
                color(greyColor) {
                    append(" in aisle $aisle")
                }
            }
        }

    }
}