package com.android.kotlincoroutines.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.kotlincoroutines.ui.screens.main.MainScreen
import com.android.kotlincoroutines.util.Constants

fun NavGraphBuilder.mainScreenComposable(
    navigateToOtherScreens: (String) -> Unit
) {
    composable(
        route = Constants.MAIN_SCREEN
    ) {
        MainScreen(navigateToOtherScreens = navigateToOtherScreens)
    }
}