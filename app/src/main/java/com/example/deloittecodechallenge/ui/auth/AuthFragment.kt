package com.example.deloittecodechallenge.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deloittecodechallenge.R
import com.example.deloittecodechallenge.databinding.FragmentAuthBinding
import com.example.deloittecodechallenge.ui.auth.adapter.ViewPagerAuthAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var viewPagerAuthAdapter: ViewPagerAuthAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAuthAdapter = ViewPagerAuthAdapter(fragment = this)
        binding.viewpager.adapter = viewPagerAuthAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.log_in_auth)
                }

                else -> {
                    tab.text = getString(R.string.register_auth)
                }
            }
        }.attach()
    }
}