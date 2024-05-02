package com.target.targetcasestudy.ui.adapter.base

import android.view.View
import androidx.annotation.LayoutRes
import com.target.targetcasestudy.R

class DefaultEmptyViewHolder(itemView: View) : AbstractViewHolder<Visitable>(itemView) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.empty_list_item
    }
    override fun bind(model: Visitable) {

    }
}