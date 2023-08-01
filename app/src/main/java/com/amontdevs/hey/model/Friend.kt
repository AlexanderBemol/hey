package com.amontdevs.hey.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Friend(
    @PrimaryKey(autoGenerate = true) var id: Int  = 0,
    var name: String,
    var city: String,
    var street: String,
    var birthday: Date,
    var lat: String,
    var lon: String,
    var cell: String,
    var email: String,
    var photo: String,
    var favorite: Boolean,
    @Ignore var bitmap: Bitmap? = null
){
    constructor() :
    this(0,"","","",Date(),"","","","","",false)
}