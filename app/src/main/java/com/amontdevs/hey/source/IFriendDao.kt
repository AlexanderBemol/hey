package com.amontdevs.hey.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amontdevs.hey.model.Friend

@Dao
interface IFriendDao {

    @Query("SELECT * FROM Friend")
    suspend fun getAll() : List<Friend>

    @Insert
    suspend fun insert(friends: List<Friend>)
}