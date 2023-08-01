package com.amontdevs.hey.repository

import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.model.TaskResult

interface IFriendRepository {
    suspend fun getFriends() : TaskResult<List<Friend>>
}