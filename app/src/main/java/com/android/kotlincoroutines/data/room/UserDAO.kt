package com.android.kotlincoroutines.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.kotlincoroutines.util.Constants

@Dao
interface UserDAO {
    @Query("Select * from ${Constants.ROOM_TABLE_NAME}")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert
    suspend fun insertAll(users: List<UserEntity>)
}