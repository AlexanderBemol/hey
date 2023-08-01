package com.amontdevs.hey.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amontdevs.hey.repository.IPreferencesRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val preferencesRepository: IPreferencesRepository
) : ViewModel() {

    val wasInitialDataPopulated = MutableLiveData<Boolean>()

    fun checkInitialDataPopulated() {
        viewModelScope.launch {
            preferencesRepository.wasInitialDataPopulated().collect{
                wasInitialDataPopulated.value = it
            }
        }
    }

}