package com.example.deloittecodechallenge.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deloittecodechallenge.R
import com.example.deloittecodechallenge.databinding.FragmentRegisterBinding
import com.example.deloittecodechallenge.utils.Constant.EMAIL
import com.example.deloittecodechallenge.utils.Constant.FULL_NAME
import com.example.deloittecodechallenge.utils.Constant.MOBILE_NUMBER
import com.example.deloittecodechallenge.utils.Constant.NATIONAL_ID
import com.example.deloittecodechallenge.utils.checkNationalID
import com.example.deloittecodechallenge.utils.checkPhoneNumber
import com.example.deloittecodechallenge.utils.disableButton
import com.example.deloittecodechallenge.utils.enableButton
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val paperPreference by lazy { Paper.book() }

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
            if (validationPassword() && validationEmail()) {
                paperPreference.write(FULL_NAME, binding.editTextFullName.text.toString().trim())
                paperPreference.write(EMAIL, binding.editTextEmail.text.toString().trim())
                paperPreference.write(
                    NATIONAL_ID,
                    binding.editTextNationalId.text.toString().trim()
                )
                paperPreference.write(
                    MOBILE_NUMBER,
                    binding.editTextPhoneNumber.text.toString().trim()
                )
                findNavController().navigate(R.id.action_authFragment_to_dashboardFragment)
            }
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

    private fun validationEmail(): Boolean {
        val email = binding.editTextEmail.text.toString().trim()
        return if (email.isEmpty()) {
            binding.editTextEmail.error = "Email is required"
            true
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Invalid email address"
            false
        } else {
            true
        }
    }

    private fun validationPassword(): Boolean {
        val password = binding.editTextPassword.text.toString().trim()
        return if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is required"
            false
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.error =
                "At least 8 characters long\n" + "Contains at least one digit ([0-9])\n" + "Contains at least one lowercase letter ([a-z])\n" + "Contains at least one uppercase letter ([A-Z])\n" + "Contains at least one special character from @#\$%^&+=\n" + "Does not contain whitespace characters"
            false
        } else {
            true
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val pattern: Pattern =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        return pattern.matcher(password).matches()
    }
}