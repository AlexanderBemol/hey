package com.amontdevs.hey.source

import com.amontdevs.hey.model.RandomUserResult
import retrofit2.Response

interface IRandomUserRemote {
    suspend fun getRandomUsers() : Response<RandomUserResult>
}