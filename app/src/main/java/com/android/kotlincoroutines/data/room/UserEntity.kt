package com.android.kotlincoroutines.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.kotlincoroutines.util.Constants

@Entity(tableName = Constants.ROOM_TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String
)