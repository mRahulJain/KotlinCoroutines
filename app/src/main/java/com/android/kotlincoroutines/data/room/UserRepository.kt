package com.android.kotlincoroutines.data.room

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UserRepository @Inject constructor(
    private val userDAO: UserDAO
) {
    suspend fun getAllUsers() = userDAO.getAllUsers()
    suspend fun addAllUsers(users: List<UserEntity>) = userDAO.insertAll(users)
}