package com.android.kotlincoroutines.ui.screens.timeout

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
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class TimeoutViewModel @Inject constructor(
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
                withTimeout(100) {
                    val usersFromApi = apiHelper.getUsers()
                    _uiState.postValue(UiState.Success(usersFromApi))
                }
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }
}