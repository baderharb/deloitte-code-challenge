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
import com.example.deloittecodechallenge.databinding.FragmentLoginBinding
import com.example.deloittecodechallenge.utils.Constant.EMAIL
import com.example.deloittecodechallenge.utils.afterTextChanged
import com.example.deloittecodechallenge.utils.disableButton
import com.example.deloittecodechallenge.utils.enableButton
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val paperPreference by lazy { Paper.book() }

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
            if (validationPassword() && validationEmail()) {
                paperPreference.write(EMAIL, binding.editTextEmail.text.toString().trim())
                findNavController().navigate(R.id.action_authFragment_to_dashboardFragment)
            }
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