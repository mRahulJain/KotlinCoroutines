package com.android.kotlincoroutines.ui.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.util.UiState

@Composable
fun UserListScreen(
    uiState: UiState<List<ApiUser>>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is UiState.Loading -> {
                UserScreenListLoading()
            }
            is UiState.Error -> {
                UserListScreenError(errorMessage = uiState.message)
            }
            is UiState.Success -> {
                UserScreenListSuccess(users = uiState.data)
            }
        }
    }
}