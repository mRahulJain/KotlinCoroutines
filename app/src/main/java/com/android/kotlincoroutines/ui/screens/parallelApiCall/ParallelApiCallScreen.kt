package com.android.kotlincoroutines.ui.screens.parallelApiCall

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.ui.screens.common.UserListScreen
import com.android.kotlincoroutines.util.UiState

@Composable
fun ParallelApiCallScreen(
    viewModel: ParallelApiCallViewModel = hiltViewModel()
) {
    val uiStateLiveData = viewModel.uiState
    val uiState: UiState<List<ApiUser>>? by uiStateLiveData.observeAsState(uiStateLiveData.value)
    uiState?.let {
        UserListScreen(uiState = it)
    }
}