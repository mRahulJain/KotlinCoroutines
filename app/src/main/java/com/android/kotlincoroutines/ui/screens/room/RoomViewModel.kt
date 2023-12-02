package com.android.kotlincoroutines.ui.screens.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.data.room.UserEntity
import com.android.kotlincoroutines.data.room.UserRepository
import com.android.kotlincoroutines.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val apiHelper: ApiHelper,
    private val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<ApiUser>>>()
    val uiState: LiveData<UiState<List<ApiUser>>> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState.Loading)
            try {
                val usersFromDb = userRepository.getAllUsers()
                if (usersFromDb.isEmpty()) {
                    val usersFromApi = apiHelper.getUsers()
                    _uiState.postValue(UiState.Success(usersFromApi))
                    userRepository.addAllUsers(usersFromApi.mapToUserEntity())
                } else
                    _uiState.postValue(UiState.Success(usersFromDb.mapToApiUsers()))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    private fun List<UserEntity>.mapToApiUsers(): List<ApiUser> {
        return this.map {
            ApiUser(
                id = it.id,
                name = it.name,
                email = it.email,
                avatar = it.avatar
            )
        }
    }

    private fun List<ApiUser>.mapToUserEntity(): List<UserEntity> {
        return this.map {
            UserEntity(
                id = it.id,
                name = it.name,
                email = it.email,
                avatar = it.avatar
            )
        }
    }
}