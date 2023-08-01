package com.amontdevs.hey.ui.homenav.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amontdevs.hey.R
import com.amontdevs.hey.databinding.FragmentHomeViewBinding
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.ui.loading.LoadingState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeView : Fragment() {
    private var _binding: FragmentHomeViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by viewModel()
    private lateinit var bdList: List<Friend>
    private lateinit var bdAdapter: HomeBdAdapter
    private lateinit var recentAdapter: HomeRecentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeViewBinding.inflate(inflater, container, false)
        observeLiveData()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getHomeData()
        bdList = listOf()
        val context = this.context
        if(context !=null) {
            setBdAdapter(context)
            setRecentAdapter(context)
        }


    }

    private fun observeLiveData(){
        val context = this.context
        if (context != null){
            viewModel.comingBirthdaysList.observe(viewLifecycleOwner) {
                bdList = it
                setBdAdapter(context)
                setRecentAdapter(context)
            }
        }
    }

    private fun setBdAdapter(context: Context){
        bdAdapter = HomeBdAdapter(bdList, context)
        binding.homeFriendsRvBd.adapter = bdAdapter
    }

    private fun setRecentAdapter(context: Context){
        recentAdapter = HomeRecentAdapter(bdList, context)
        binding.homeFriendsRvRecentCalls.adapter = recentAdapter
    }

}