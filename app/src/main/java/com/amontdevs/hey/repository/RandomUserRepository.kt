package com.amontdevs.hey.repository

import android.graphics.Bitmap
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.model.TaskResult
import com.amontdevs.hey.source.IFriendDao
import com.amontdevs.hey.source.ILocalFileManager
import com.amontdevs.hey.source.IPictureDownloader
import com.amontdevs.hey.source.IRandomUserRemote
import kotlin.Exception
import kotlin.random.Random

class RandomUserRepository(
    private val randomUserRemote: IRandomUserRemote,
    private val friendDao: IFriendDao,
    private val pictureDownloader: IPictureDownloader,
    private val localFileManager: ILocalFileManager
) : IRandomUserRepository {
    override suspend fun populateInitialData(): TaskResult<Unit> {
        return if(randomUserRemote.getRandomUsers().isSuccessful){
            //Add Network Validation?

            try {
                val users = randomUserRemote.getRandomUsers().body()
                val friendsList = mutableListOf<Friend>()
                users?.results?.forEach {
                    val photoBitmap = downloadPhoto(it.picture.large)
                    val savedPhotoPath =
                        localFileManager.savePhotoToInternal(photoBitmap,it.name.first)
                    friendsList.add(
                        Friend(
                            name = it.name.first + " " + it.name.last,
                            city = it.location.city + ", " + it.location.country,
                            street = it.location.street.name + " #" + it.location.street.number,
                            birthday = it.dob.date,
                            lat = it.location.coordinates.latitude,
                            lon = it.location.coordinates.longitude,
                            cell = it.cell,
                            email = it.email,
                            photo = savedPhotoPath,
                            favorite = Random.nextBoolean()
                        )
                    )
                }
                friendDao.insert(friendsList)
            } catch (e : Exception) {
                TaskResult.Error(e)
            }

            TaskResult.Success(Unit)
        } else {
            TaskResult.Error(Exception())
        }
    }

    private fun downloadPhoto(pictureUrl: String): Bitmap {
        when(val result = pictureDownloader.getPictureFromUrl(pictureUrl)){
            is TaskResult.Success -> {
                return result.data
            }
            is TaskResult.Error -> {
                //Maybe return a default photo
                throw result.e
            }
        }
    }
}