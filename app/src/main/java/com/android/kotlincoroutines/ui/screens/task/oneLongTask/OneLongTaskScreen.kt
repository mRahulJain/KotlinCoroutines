package com.android.kotlincoroutines.ui.screens.task.oneLongTask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.kotlincoroutines.ui.screens.task.common.LongTaskScreen
import com.android.kotlincoroutines.util.UiState

@Composable
fun OneLongTaskScreen(
    viewModel: OneLongTaskViewModel = hiltViewModel()
) {
    val uiState: UiState<String>? by viewModel.uiState.observeAsState(viewModel.uiState.value)
    uiState?.let {
        LongTaskScreen(uiState = it)
    }
}