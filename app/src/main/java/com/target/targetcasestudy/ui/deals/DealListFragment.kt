package com.target.targetcasestudy.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import com.target.targetcasestudy.R
import com.target.targetcasestudy.domain.model.Product
import com.target.targetcasestudy.ui.deals.viewmodels.DealListViewModel
import com.target.targetcasestudy.ui.adapter.DealItemAdapter
import com.target.targetcasestudy.ui.adapter.base.TargetTypesFactoryImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewModel by viewModels<DealListViewModel>()
    private val dealsAdapter by lazy {
        DealItemAdapter(
            TargetTypesFactoryImpl(
                onDealItemClick = ::onDealItemClick
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        view?.findViewById<RecyclerView>(R.id.recycler_view)?.apply {
            context?.let { ctx ->
                layoutManager = LinearLayoutManager(ctx)
                adapter = dealsAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getDeals().observe(viewLifecycleOwner) {
            dealsAdapter.submitList(it)
        }
        viewModel.getErrorMessage().observe(viewLifecycleOwner) { message ->
            view?.let { it -> Snackbar.make(it, message.orEmpty(), Snackbar.LENGTH_SHORT).show() }
        }
    }

    private fun onDealItemClick(product: Product) {
        findNavController().navigate(
            R.id.action_dealListFragment_to_dealItemFragment, bundleOf(
                "id" to product.id
            )
        )
    }
}
