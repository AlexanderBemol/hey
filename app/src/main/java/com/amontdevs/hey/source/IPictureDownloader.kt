package com.amontdevs.hey.source

import android.graphics.Bitmap
import com.amontdevs.hey.model.TaskResult

interface IPictureDownloader {
    fun getPictureFromUrl(pictureUrl: String) : TaskResult<Bitmap>
}