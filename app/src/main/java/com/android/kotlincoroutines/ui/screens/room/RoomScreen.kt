package com.android.kotlincoroutines.ui.screens.room

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.ui.screens.common.UserListScreen
import com.android.kotlincoroutines.util.UiState

@Composable
fun RoomScreen(
    viewModel: RoomViewModel = hiltViewModel()
) {
    val uiState: UiState<List<ApiUser>>? by viewModel.uiState.observeAsState(viewModel.uiState.value)
    uiState?.let {
        UserListScreen(uiState = it)
    }
}