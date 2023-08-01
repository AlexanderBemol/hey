package com.amontdevs.hey.source

import android.graphics.Bitmap

interface ILocalFileManager {
    fun savePhotoToInternal(image: Bitmap, username: String) : String
    fun readPhotoFromInternal(filename: String) : Bitmap
}