package com.amontdevs.hey.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.amontdevs.hey.model.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepository(
    private val context: Context
) : IPreferencesRepository {
    private val Context.preferencesDataStore :
            DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE_NAME)

    override suspend fun wasInitialDataPopulated(): Flow<Boolean> {
        return context.preferencesDataStore.data.map {
            it[PreferencesKeys.WAS_INITIAL_DATA_POPULATED] ?: false
        }
    }

    override suspend fun setInitialDataPopulated(value: Boolean) {
        context.preferencesDataStore.edit { it[PreferencesKeys.WAS_INITIAL_DATA_POPULATED] = value }
    }
}

object PreferencesKeys {
    val WAS_INITIAL_DATA_POPULATED =
        booleanPreferencesKey(Constants.PREFERENCE_KEY_WAS_INITIAL_DATA_POPULATED)
}