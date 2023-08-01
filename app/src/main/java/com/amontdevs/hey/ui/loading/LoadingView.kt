package com.amontdevs.hey.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amontdevs.hey.R
import com.amontdevs.hey.databinding.FragmentLoadingViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadingView : Fragment() {
    private var _binding: FragmentLoadingViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoadingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoadingViewBinding.inflate(inflater, container, false)
        observeLiveData()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        populateInitialData()
        binding.loadingBtTryAgain.setOnClickListener {
            populateInitialData()
        }
    }

    private fun populateInitialData() {
        updateScreen(LoadingState.LOADING)
        viewModel.importInitialData()
    }

    private fun observeLiveData() {
        val context = this.context
        if (context != null){
            viewModel.loadingState.observe(viewLifecycleOwner) {
                if(it == LoadingState.SUCCESS)
                    findNavController().navigate(R.id.action_loadingView_to_homeNavigation)
                else updateScreen(it)
            }
        }
    }

    private fun updateScreen(loadingState: LoadingState){
        if(loadingState == LoadingState.LOADING){
            binding.loadingAnimation.setAnimation("lf_loading3.json")
            binding.loadingAnimation.playAnimation()
            binding.loadingDescription.text  = getString(R.string.loading_description)
            binding.loadingBtTryAgain.visibility = View.GONE
        } else {
            binding.loadingAnimation.setAnimation("lf_error.json")
            binding.loadingAnimation.playAnimation()
            binding.loadingDescription.text  = getString(R.string.loading_description_error)
            binding.loadingBtTryAgain.visibility = View.VISIBLE
        }
    }

}