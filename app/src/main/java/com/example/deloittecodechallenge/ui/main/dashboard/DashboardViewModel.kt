package com.example.deloittecodechallenge.ui.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.data.dto.toProduct
import com.example.deloittecodechallenge.data.product.ProductDataSource
import com.example.deloittecodechallenge.data.remote.DefaultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productRepository: ProductDataSource
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Product>> =
        MutableStateFlow(listOf())
    val uiState: StateFlow<List<Product>> = _uiState.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            when (val result = productRepository.fetchAllRemoteProducts()) {
                is DefaultResponse.Success -> {
                    val productList: List<Product> = result.body.results?.mapNotNull {
                        it?.toProduct()
                    } ?: listOf()

                    _uiState.update {
                        productList
                    }

//                    productRepository.addProductsToLocalDB(productList)
                }

                is DefaultResponse.Empty -> {

                }

                is DefaultResponse.Fail -> {

                }
            }
        }
    }
}