package com.amontdevs.hey.repository

import com.amontdevs.hey.model.TaskResult

interface IRandomUserRepository {
    suspend fun populateInitialData() : TaskResult<Unit>
}