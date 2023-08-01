package com.amontdevs.hey.ui.homenav.friends

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.amontdevs.hey.databinding.FragmentFriendsViewBinding
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.ui.homenav.home.HomeRecentAdapter
import com.amontdevs.hey.ui.homenav.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FriendsView : Fragment() {
    private var _binding: FragmentFriendsViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FriendsViewModel by viewModel()
    private lateinit var friendsList : List<Friend>
    private lateinit var helperFriendsList : List<Friend>
    private lateinit var friendsAdapter: FriendsItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendsViewBinding.inflate(inflater, container, false)
        observeLiveData()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        binding.chipFav.isChecked = false
    }

    override fun onStart() {
        super.onStart()
        super.onStart()
        viewModel.getFriendsData()
        friendsList = listOf()
        helperFriendsList = listOf()
        val context = this.context
        if(context !=null) {
            setFriendsAdapter(context)

            binding.searchInput.editText?.addTextChangedListener {
                val txt = binding.searchInput.editText!!.text.toString()
                helperFriendsList = friendsList.filter{it.name.contains(txt,true)}
                setFriendsAdapter(context)
            }
            binding.searchInput.setEndIconOnClickListener {
                helperFriendsList = friendsList
                setFriendsAdapter(context)
                binding.searchInput.editText?.text?.clear()
            }
            binding.chipFav.setOnClickListener {
                helperFriendsList = if(binding.chipFav.isChecked)
                    friendsList.filter { it.favorite } else friendsList
                setFriendsAdapter(context)
            }
        }

    }

    private fun observeLiveData(){
        val context = this.context
        if (context != null){
            viewModel.friendsList.observe(viewLifecycleOwner) {
                friendsList = it
                helperFriendsList = it
                setFriendsAdapter(context)
            }
        }
    }

    private fun setFriendsAdapter(context: Context){
        friendsAdapter = FriendsItemAdapter(helperFriendsList, context)
        binding.rvFriends.adapter = friendsAdapter
    }
}