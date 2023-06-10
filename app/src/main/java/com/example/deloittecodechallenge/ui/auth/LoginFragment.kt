package com.example.deloittecodechallenge.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deloittecodechallenge.R
import com.example.deloittecodechallenge.databinding.FragmentLoginBinding
import com.example.deloittecodechallenge.utils.afterTextChanged
import com.example.deloittecodechallenge.utils.disableButton
import com.example.deloittecodechallenge.utils.enableButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        validate()
        initListeners()
    }

    private fun initListeners() {
        binding.editTextPassword.doOnTextChanged { it, _, _, _ ->
            validate()
        }

        binding.editTextPassword.afterTextChanged {
            val result: String = it.toString().replace(" ", "")
            if (!it.toString().equals(result)) {
                binding.editTextPassword.setText(result)
                binding.editTextPassword.setSelection(result.length)
            }
        }

        binding.editTextEmail.doOnTextChanged { it, _, _, _ ->
            validate()
        }

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_dashboardFragment)
        }
    }

    private fun validate() {
        if (binding.editTextPassword.text.toString()
                .isNotEmpty() && binding.editTextEmail.text.toString().isNotEmpty()
        ) {
            binding.buttonLogin.enableButton()
        } else {
            binding.buttonLogin.disableButton()
        }
    }
}