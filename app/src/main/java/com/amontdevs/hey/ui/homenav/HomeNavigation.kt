package com.amontdevs.hey.ui.homenav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.amontdevs.hey.R
import com.amontdevs.hey.databinding.FragmentHomeNavigationContainerBinding
import com.amontdevs.hey.databinding.FragmentLoadingViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeNavigation : Fragment() {
    private var _binding: FragmentHomeNavigationContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeNavigationContainerBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        setUp()
    }

    private fun setUp(){
        val navHost = binding.navHostFragment.getFragment<NavHostFragment>()
        binding.bottomNavView.setupWithNavController(navHost.navController)
    }
}