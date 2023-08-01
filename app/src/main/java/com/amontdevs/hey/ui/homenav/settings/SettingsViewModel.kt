package com.amontdevs.hey.ui.homenav.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val packageName = getApplication<Application>().applicationContext .packageName
    fun resetApp(){
        viewModelScope.launch {
            try {
                val runtime = Runtime.getRuntime()
                withContext(Dispatchers.IO) {
                    runtime.exec("pm clear $packageName")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}