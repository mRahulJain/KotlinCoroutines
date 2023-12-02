package com.android.kotlincoroutines.ui.screens.task.twoLongTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TwoLongTaskViewModel: ViewModel() {

    private val _uiState = MutableLiveData<UiState<String>>()
    val uiState: LiveData<UiState<String>> = _uiState

    init {
        startTwoLongRunningTask()
    }

    private fun startTwoLongRunningTask() {
        viewModelScope.launch {
            try {
                val deferredTaskOneResult = async { doFirstLongRunningTask() }
                val deferredTaskTwoResult = async { doSecondLongRunningTask() }
                val combinedResult = deferredTaskOneResult.await() + deferredTaskTwoResult.await()
                _uiState.postValue(UiState.Success("Task Completed - $combinedResult"))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    private suspend fun doFirstLongRunningTask(): Int {
        return withContext(Dispatchers.Default) {
            delay(2000)
            return@withContext 10
        }
    }

    private suspend fun doSecondLongRunningTask(): Int {
        return withContext(Dispatchers.Default) {
            delay(2000)
            return@withContext 5
        }
    }
}