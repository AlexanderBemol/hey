package com.amontdevs.hey.source

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.amontdevs.hey.model.TaskResult
import java.net.URL

class PictureDownloader : IPictureDownloader {
    override fun getPictureFromUrl(pictureUrl: String) : TaskResult<Bitmap> {
        return try{
            val url = URL(pictureUrl)
            TaskResult.Success(
                BitmapFactory.decodeStream(
                    url.openConnection().getInputStream()
                )
            )
        } catch (e : Exception){
            TaskResult.Error(e)
        }
    }
}