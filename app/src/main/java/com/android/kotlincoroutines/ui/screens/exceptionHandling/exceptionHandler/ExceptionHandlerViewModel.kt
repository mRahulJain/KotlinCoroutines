package com.android.kotlincoroutines.ui.screens.exceptionHandling.exceptionHandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExceptionHandlerViewModel @Inject constructor(
    private val apiHelper: ApiHelper
): ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<ApiUser>>>()
    val uiState: LiveData<UiState<List<ApiUser>>> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _uiState.postValue(UiState.Error(e.toString()))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _uiState.postValue(UiState.Loading)
            val usersFromApi = apiHelper.getUsersWithError()
            _uiState.postValue(UiState.Success(usersFromApi))
        }
    }
}