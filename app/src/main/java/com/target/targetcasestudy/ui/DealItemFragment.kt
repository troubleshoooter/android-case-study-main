package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.DealItemAdapter
import com.target.targetcasestudy.ui.adapter.base.TargetTypesFactoryImpl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealItemFragment : Fragment() {

    private val viewModel by viewModels<DealItemViewModel>()
    private val itemAdapter by lazy {
        DealItemAdapter(TargetTypesFactoryImpl())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_product_details).adapter = itemAdapter
        setUpObserver()
    }

    private fun setUpObserver() {
        val id = arguments?.getInt("id")
        id?.let {
            viewModel.getDeal(id)
        }

        viewModel.dealsLiveData.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)
        }
    }
}
