package com.android.kotlincoroutines.navigation

import androidx.navigation.NavHostController
import com.android.kotlincoroutines.util.Constants

class Screens(navController: NavHostController) {

    val navigateToOtherScreens: (String) -> Unit = {
        navController.navigate(route = "${Constants.OTHER_SCREEN}/$it")
    }

}