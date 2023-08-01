package com.amontdevs.hey.ui.loading

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amontdevs.hey.model.TaskResult
import com.amontdevs.hey.repository.IPreferencesRepository
import com.amontdevs.hey.repository.IRandomUserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadingViewModel(
    private val userRepository: IRandomUserRepository,
    private val preferencesRepository: IPreferencesRepository
) : ViewModel() {

    val loadingState = MutableLiveData<LoadingState>()

    fun importInitialData(){
        loadingState.value = LoadingState.LOADING
        CoroutineScope(Dispatchers.IO).launch{
            when(userRepository.populateInitialData()){
                is TaskResult.Success -> {
                    preferencesRepository.setInitialDataPopulated(true)
                    loadingState.postValue(LoadingState.SUCCESS)
                }
                is TaskResult.Error -> {
                    loadingState.postValue(LoadingState.ERROR)
                }
            }
        }
    }
}