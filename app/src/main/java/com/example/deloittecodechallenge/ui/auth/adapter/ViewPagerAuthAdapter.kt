package com.example.deloittecodechallenge.ui.auth.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deloittecodechallenge.ui.auth.LoginFragment
import com.example.deloittecodechallenge.ui.auth.RegisterFragment

class ViewPagerAuthAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginFragment()
            }

            else -> {
                RegisterFragment()
            }
        }
    }
}
