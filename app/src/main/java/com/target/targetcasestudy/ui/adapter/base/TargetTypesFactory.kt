package com.target.targetcasestudy.ui.adapter.base

import android.view.View
import com.target.targetcasestudy.data.source.remote.model.Product

interface TargetTypesFactory {
    fun deals(data: Product): Int
    fun empty(): Int
    fun createViewHolder(view: View, type: Int): AbstractViewHolder<*>
}
