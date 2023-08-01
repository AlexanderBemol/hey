package com.amontdevs.hey.repository

import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.model.TaskResult
import com.amontdevs.hey.source.IFriendDao
import com.amontdevs.hey.source.ILocalFileManager
import com.amontdevs.hey.source.LocalFileManager

class FriendRepository(
    private val friendDao: IFriendDao,
    private val localFileManager: ILocalFileManager
) : IFriendRepository {
    override suspend fun getFriends() : TaskResult<List<Friend>>{
        return try {
            val listOfFriends = friendDao.getAll()
            listOfFriends.map {
                it.bitmap = localFileManager.readPhotoFromInternal(it.photo)
            }
            TaskResult.Success(listOfFriends)
        } catch (e : Exception){
            TaskResult.Error(e)
        }
    }
}