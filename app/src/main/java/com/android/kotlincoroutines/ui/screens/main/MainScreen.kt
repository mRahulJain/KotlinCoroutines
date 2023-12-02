package com.android.kotlincoroutines.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.kotlincoroutines.util.Constants

@Composable
fun MainScreen(
    navigateToOtherScreens: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            items(items = Constants.list) {
                MainScreenListItem(
                    itemName = it,
                    onItemClick = { navigateToOtherScreens(it) }
                )
            }
        }
    }
}

@Composable
private fun MainScreenListItem(
    itemName: String,
    onItemClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        onClick = { onItemClick() }
    ) {
        Text(text = itemName, textAlign = TextAlign.Center)
    }
}