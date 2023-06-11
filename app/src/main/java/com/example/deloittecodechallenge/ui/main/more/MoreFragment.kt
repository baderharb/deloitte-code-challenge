package com.example.deloittecodechallenge.ui.main.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.deloittecodechallenge.databinding.FragmentMoreBinding
import com.example.deloittecodechallenge.utils.Constant.AR
import com.example.deloittecodechallenge.utils.Constant.EMAIL
import com.example.deloittecodechallenge.utils.Constant.EN
import com.example.deloittecodechallenge.utils.Constant.FULL_NAME
import com.example.deloittecodechallenge.utils.Constant.LIST_PREFERENCE_FRAGMENT_MORE_LANGUAGE
import com.example.deloittecodechallenge.utils.Constant.MOBILE_NUMBER
import com.example.deloittecodechallenge.utils.Constant.NATIONAL_ID
import com.example.deloittecodechallenge.utils.annotaion.ShowNavController
import com.example.deloittecodechallenge.utils.prefs.Prefs
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper
import java.util.Locale
import javax.inject.Inject

@ShowNavController
@AndroidEntryPoint
class MoreFragment : Fragment() {

    @Inject
    lateinit var prefs: Prefs
    private val preferences by lazy { PreferenceManager.getDefaultSharedPreferences(requireContext()) }
    private lateinit var binding: FragmentMoreBinding
    private var isChangeLanguage: Boolean = false
    private val paperPreference by lazy { Paper.book() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        initViewsForLanguage()
        initViews()
        initClicks()
    }

    private fun initClicks() {
        binding.constraintLayoutLogOut.setOnClickListener {
            Paper.book().destroy()
            recreateUi()
        }

        binding.radioButtonEn.setOnClickListener {
            if (Locale.getDefault().language != EN) {
                preferences.edit {
                    putString(LIST_PREFERENCE_FRAGMENT_MORE_LANGUAGE, EN)
                }
                isChangeLanguage = true
                recreateUi()
            }
        }

        binding.radioButtonAr.setOnClickListener {
            if (Locale.getDefault().language != AR) {
                preferences.edit {
                    putString(LIST_PREFERENCE_FRAGMENT_MORE_LANGUAGE, AR)
                }
                isChangeLanguage = true
                recreateUi()
            }
        }
    }

    private fun initViews() {
        binding.fullName = paperPreference.read(FULL_NAME, "")
        binding.email = paperPreference.read(EMAIL, "")
        binding.nationalId = paperPreference.read(NATIONAL_ID, "")
        binding.phoneNumber = paperPreference.read(MOBILE_NUMBER, "")
        binding.executePendingBindings()
    }

    private fun initViewsForLanguage() {
        binding.getCurrentLanguage = prefs.getCurrentLanguage
        binding.executePendingBindings()
    }

    private fun recreateUi() {
        startActivity(Intent.makeRestartActivityTask(requireActivity().componentName))
        requireActivity().overridePendingTransition(
            android.R.anim.fade_in, android.R.anim.fade_out
        )
    }
}