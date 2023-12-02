package com.android.kotlincoroutines.ui.screens.parallelApiCall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParallelApiCallViewModel @Inject constructor(
    private val apiHelper: ApiHelper
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
                val usersFromApi = viewModelScope.async { apiHelper.getUsers() }
                val moreUsersFromApi = viewModelScope.async { apiHelper.getMoreUsers() }
                val allUsers = mutableListOf<ApiUser>()
                allUsers.addAll(usersFromApi.await())
                allUsers.addAll(moreUsersFromApi.await())
                _uiState.postValue(UiState.Success(allUsers))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }
}