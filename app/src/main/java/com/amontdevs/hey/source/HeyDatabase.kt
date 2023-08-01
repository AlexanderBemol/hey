package com.amontdevs.hey.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.utils.RoomConverters

@Database(entities = [Friend::class] , version = 1)
@TypeConverters(RoomConverters::class)
abstract class HeyDatabase : RoomDatabase() {
    abstract fun friendsDao() : IFriendDao
}