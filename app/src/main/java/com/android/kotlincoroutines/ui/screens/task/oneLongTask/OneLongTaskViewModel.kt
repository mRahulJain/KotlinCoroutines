package com.android.kotlincoroutines.ui.screens.task.oneLongTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class OneLongTaskViewModel: ViewModel() {

    private val _uiState = MutableLiveData<UiState<String>>()
    val uiState: LiveData<UiState<String>> = _uiState

    init {
        startLongRunningTask()
    }

    private fun startLongRunningTask() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)
            try {
                doALongRunningTask()
                _uiState.postValue(UiState.Success("Task Completed"))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    private suspend fun doALongRunningTask() {
        withContext(Dispatchers.Default) {
            delay(5000)
        }
    }

}