package com.target.targetcasestudy.ui.adapter

import android.view.View
import androidx.annotation.LayoutRes
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder

class DealsViewHolder(itemView: View) : AbstractViewHolder<DealsUiModel>(itemView) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.deal_list_item
    }

    override fun bind(model: DealsUiModel) {
        with(itemView) {
        }
    }
}