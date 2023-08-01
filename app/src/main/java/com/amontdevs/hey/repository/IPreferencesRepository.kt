package com.amontdevs.hey.repository

import kotlinx.coroutines.flow.Flow

interface IPreferencesRepository {
    suspend fun wasInitialDataPopulated() : Flow<Boolean>
    suspend fun setInitialDataPopulated(value: Boolean)
}