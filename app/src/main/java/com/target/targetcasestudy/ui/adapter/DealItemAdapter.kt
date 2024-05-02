package com.target.targetcasestudy.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.target.targetcasestudy.ui.adapter.base.AbstractViewHolder
import com.target.targetcasestudy.ui.adapter.base.DefaultEmptyViewHolder
import com.target.targetcasestudy.ui.adapter.base.TargetTypesFactory
import com.target.targetcasestudy.ui.adapter.base.Visitable

class DealItemAdapter(
    private val typesFactory: TargetTypesFactory
) :
    ListAdapter<Visitable, AbstractViewHolder<Visitable>>(BaseDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<Visitable> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typesFactory.createViewHolder(view, viewType) as AbstractViewHolder<Visitable>
    }

    override fun onBindViewHolder(
        viewHolder: AbstractViewHolder<Visitable>,
        position: Int
    ) {
        val item = getItem(position)
        viewHolder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount <= 0) DefaultEmptyViewHolder.LAYOUT else getItem(position).type(
            typesFactory
        )
    }
}


class BaseDiffUtil :
    DiffUtil.ItemCallback<Visitable>() {
    override fun areItemsTheSame(
        old: Visitable,
        new: Visitable
    ): Boolean {
        return old == new
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        old: Visitable,
        new: Visitable
    ): Boolean {
        return old == new
    }

}