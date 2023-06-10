package com.example.deloittecodechallenge.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deloittecodechallenge.R
import com.example.deloittecodechallenge.databinding.FragmentRegisterBinding
import com.example.deloittecodechallenge.utils.checkNationalID
import com.example.deloittecodechallenge.utils.checkPhoneNumber
import com.example.deloittecodechallenge.utils.disableButton
import com.example.deloittecodechallenge.utils.enableButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
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
        binding.editTextFullName.doOnTextChanged { it, _, _, _ ->
            validate()
        }

        binding.editTextEmail.doOnTextChanged { it, _, _, _ ->
            validate()
        }

        binding.editTextNationalId.doOnTextChanged { it, _, _, _ ->
            if (binding.editTextNationalId.text?.isNotEmpty() == true) {
                binding.editTextNationalId.checkNationalID()
            }
            validate()
        }

        binding.editTextPhoneNumber.doOnTextChanged { it, _, _, _ ->
            if (binding.editTextPhoneNumber.text?.isNotEmpty() == true) {
                binding.editTextPhoneNumber.checkPhoneNumber()
            }
            validate()
        }

        binding.editTextPassword.doOnTextChanged { it, _, _, _ ->
            validate()
        }

        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_dashboardFragment)
        }
    }

    private fun validate() {
        if (binding.editTextFullName.text.toString()
                .isNotEmpty() && binding.editTextEmail.text.toString()
                .isNotEmpty() && binding.editTextNationalId.text.toString()
                .isNotEmpty() && binding.editTextPhoneNumber.text.toString()
                .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
        ) {
            binding.buttonRegister.enableButton()
        } else {
            binding.buttonRegister.disableButton()
        }
    }
}