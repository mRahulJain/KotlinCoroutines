package com.android.kotlincoroutines.data.api

import com.android.kotlincoroutines.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

    suspend fun getMoreUsers(): List<ApiUser>

    suspend fun getUsersWithError(): List<ApiUser>

}

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers() = apiService.getUsers()

    override suspend fun getMoreUsers() = apiService.getMoreUsers()

    override suspend fun getUsersWithError() = apiService.getUsersWithError()

}