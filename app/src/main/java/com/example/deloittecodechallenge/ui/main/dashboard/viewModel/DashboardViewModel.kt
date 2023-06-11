package com.example.deloittecodechallenge.ui.main.dashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deloittecodechallenge.data.Product
import com.example.deloittecodechallenge.data.dto.toProduct
import com.example.deloittecodechallenge.data.product.ProductDataSource
import com.example.deloittecodechallenge.data.remote.DefaultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productRepository: ProductDataSource
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val uiState: StateFlow<List<Product>> = _uiState.asStateFlow()

    private val _progressSharedFlow = MutableSharedFlow<Boolean>()
    val progressSharedFlow = _progressSharedFlow.asSharedFlow()

    fun getProducts() {
        viewModelScope.launch {
            _progressSharedFlow.emit(true)
            when (val result = productRepository.fetchAllRemoteProducts()) {
                is DefaultResponse.Success -> {
                    val productList: List<Product> = result.body.results?.mapNotNull {
                        it?.toProduct()
                    } ?: listOf()

                    _uiState.emit(productList)
                }

                is DefaultResponse.Empty -> {

                }

                is DefaultResponse.Fail -> {

                }
            }
            _progressSharedFlow.emit(false)
        }
    }
}