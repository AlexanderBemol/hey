package com.amontdevs.hey.source

import com.amontdevs.hey.model.RandomUser
import com.amontdevs.hey.model.RandomUserResult
import com.amontdevs.hey.model.TaskResult
import retrofit2.Response

class RandomUserRemoteImpl(
    private val apiService: IRandomUserService
) : IRandomUserRemote {
    override suspend fun getRandomUsers() :
            Response<RandomUserResult> = apiService.getRandomUsers()
}