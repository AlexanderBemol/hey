package com.amontdevs.hey.source

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.amontdevs.hey.model.MissingPhotoException
import com.amontdevs.hey.utils.toTimestampFilename
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.Date

class LocalFileManager(
    private val context: Context
) : ILocalFileManager{

    override fun savePhotoToInternal(image: Bitmap, username: String) : String {
        val filename = Date().toTimestampFilename(username)
        val file = File(context.filesDir,filename)
        val outputStream = FileOutputStream(file) as OutputStream
        image.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        outputStream.flush()
        outputStream.close()
        return filename
    }

    override fun readPhotoFromInternal(filename: String) : Bitmap {
        val file = File(context.filesDir,filename)
        if(file.exists()) return BitmapFactory.decodeFile(file.path)
        else throw(MissingPhotoException(filename))
    }

}