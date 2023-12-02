package com.android.kotlincoroutines.ui.screens.seriesApiCall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesApiCallViewModel @Inject constructor(
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
                val usersFromApi = apiHelper.getUsers()
                val moreUsersFromApi = apiHelper.getMoreUsers()
                val allUsers = mutableListOf<ApiUser>()
                allUsers.addAll(usersFromApi)
                allUsers.addAll(moreUsersFromApi)
                _uiState.postValue(UiState.Success(allUsers))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }
}