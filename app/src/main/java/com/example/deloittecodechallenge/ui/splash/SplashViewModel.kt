package com.example.deloittecodechallenge.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deloittecodechallenge.data.auth.UserDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SplashViewModel"

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserDataSource
) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            val result = userRepository.getUserLocal()
            Log.d(TAG, "getData: result $result")
        }
    }

}