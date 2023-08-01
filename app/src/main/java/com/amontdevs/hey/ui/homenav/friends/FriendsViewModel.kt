package com.amontdevs.hey.ui.homenav.friends

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.model.TaskResult
import com.amontdevs.hey.repository.IFriendRepository
import com.amontdevs.hey.utils.daysTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class FriendsViewModel(
    private val friendRepository : IFriendRepository
) : ViewModel() {
    val friendsList = MutableLiveData<List<Friend>>()

    fun getFriendsData(){
        CoroutineScope(Dispatchers.IO).launch {
            when(val result = friendRepository.getFriends()){
                is TaskResult.Success -> {
                    val sortedList = result.data.sortedBy { it.name }
                    friendsList.postValue(sortedList)
                }
                is TaskResult.Error -> {
                    //TO DO
                }
            }
        }
    }
}