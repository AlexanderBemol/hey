package com.amontdevs.hey.ui.homenav.home

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

class HomeViewModel(
    private val friendRepository : IFriendRepository
) : ViewModel() {
    val comingBirthdaysList = MutableLiveData<List<Friend>>()

    fun getHomeData(){
        CoroutineScope(Dispatchers.IO).launch {
            when(val result = friendRepository.getFriends()){
                is TaskResult.Success -> {
                    val sortedList = result.data.sortedBy { Date().daysTo(it.birthday) }
                    comingBirthdaysList.postValue(sortedList.subList(0,6))

                }
                is TaskResult.Error -> {
                    //TO DO
                }
            }
        }
    }
}