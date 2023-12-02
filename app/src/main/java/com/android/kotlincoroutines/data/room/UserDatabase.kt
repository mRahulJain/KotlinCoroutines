package com.android.kotlincoroutines.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.kotlincoroutines.util.Constants

@Database(
    entities = [UserEntity::class],
    version = Constants.ROOM_DATABASE_VERSION,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}