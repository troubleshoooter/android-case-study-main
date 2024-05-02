package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapter.DealItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewmodel by viewModels<DealListViewModel>()
    private val dealsAdapter by lazy {
        DealItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        view.findViewById<RecyclerView>(R.id.recycler_view)?.apply {
            context?.let { ctx ->
                layoutManager = LinearLayoutManager(ctx)
                adapter = dealsAdapter
            }
        }
        viewmodel.getDeals()
        viewmodel.dealsLiveData.observe(viewLifecycleOwner) {
            dealsAdapter.submitList(it)
        }
        return view
    }
}
