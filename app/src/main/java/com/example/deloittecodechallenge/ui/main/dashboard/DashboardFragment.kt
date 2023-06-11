package com.example.deloittecodechallenge.ui.main.dashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.databinding.FragmentDashboardBinding
import com.example.deloittecodechallenge.ui.main.dashboard.adapter.ProductsAdapter
import com.example.deloittecodechallenge.ui.main.dashboard.viewModel.DashboardViewModel
import com.example.deloittecodechallenge.utils.annotaion.ShowNavController
import com.example.deloittecodechallenge.utils.hide
import com.example.deloittecodechallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ShowNavController
@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private val productsAdapter: ProductsAdapter = ProductsAdapter()
    private lateinit var productList: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!this::binding.isInitialized) {
            binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
            init()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun init() {
        initShoot()
        initListener()
        initBackPressedListener()
        initAdapter()
    }

    private fun initShoot() {
        viewModel.getProducts()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::handleSuccess)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.progressSharedFlow.collect(::handleProgress)
            }
        }
    }

    private fun handleSuccess(products: List<Product>) {
        productsAdapter.setItems(products)
        productList = products
    }

    private fun handleProgress(isShowing: Boolean) {
        if (isShowing) {
            binding.lottieViewListLoading.show()
        } else {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.lottieViewListLoading.hide()
        }
    }

    private fun initAdapter() {
        binding.rc.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        productsAdapter.setOnItemClickListener(object : ProductsAdapter.OnItemClickListener {
            override fun onItemClicked(view: View, item: Product, position: Int) {
                Toast.makeText(requireContext(), "${item.title}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initListener() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(query: Editable?) {
                doOnTextChange(query = query.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
                doOnTextChange(query = query.toString())
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            initShoot()
        }
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

    private fun doOnTextChange(query: String?) {
        if (query.isNullOrEmpty()) {
            productsAdapter.setItems(productList)
            checkNoResult()
        } else {
            val filteredList = productList.filter {
                it.title?.lowercase()?.contains(query.toString().lowercase()) == true
            }
            productsAdapter.setItems(filteredList)
            checkNoResult()
        }
    }

    private fun checkNoResult() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (productsAdapter.isFilterResultEmpty()) {
                binding.textViewNoResult.visibility = View.VISIBLE
            } else {
                binding.textViewNoResult.visibility = View.GONE
            }
        }, 10)
    }
}