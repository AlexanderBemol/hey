package com.amontdevs.hey.source

import com.amontdevs.hey.model.RandomUserResult
import retrofit2.Response
import retrofit2.http.GET

interface IRandomUserService {
    @GET("?results=75")
    suspend fun getRandomUsers() : Response<RandomUserResult>

}