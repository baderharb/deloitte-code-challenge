package com.example.deloittecodechallenge.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.databinding.FragmentDashboardBinding
import com.example.deloittecodechallenge.utils.ShowNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ShowNavController
@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    private val productsAdapter: ProductsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackPressedListener()

        lifecycleScope.launch {
            viewModel.uiState.collect {
                productsAdapter.clear()
                productsAdapter.setItems(it)
            }
        }

        viewModel.getProducts()


        binding.rc.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        productsAdapter.setOnItemClickListener(object :
            ProductsAdapter.OnItemClickListener {
            override fun onItemClicked(view: View, item: Product, position: Int) {
                Toast.makeText(requireContext(), "${item.title}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initBackPressedListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )
    }
}