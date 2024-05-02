package com.target.targetcasestudy.ui.adapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<in T: Visitable>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: T)
}
