package com.android.kotlincoroutines.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.kotlincoroutines.ui.screens.exceptionHandling.exceptionHandler.ExceptionHandlerScreen
import com.android.kotlincoroutines.ui.screens.exceptionHandling.supervisor.SupervisorScreen
import com.android.kotlincoroutines.ui.screens.exceptionHandling.tryCatch.TryCatchScreen
import com.android.kotlincoroutines.ui.screens.parallelApiCall.ParallelApiCallScreen
import com.android.kotlincoroutines.ui.screens.room.RoomScreen
import com.android.kotlincoroutines.ui.screens.seriesApiCall.SeriesApiCallScreen
import com.android.kotlincoroutines.ui.screens.singleApiCall.SingleApiCallScreen
import com.android.kotlincoroutines.ui.screens.task.oneLongTask.OneLongTaskScreen
import com.android.kotlincoroutines.ui.screens.task.twoLongTask.TwoLongTaskScreen
import com.android.kotlincoroutines.ui.screens.timeout.TimeoutScreen
import com.android.kotlincoroutines.util.Constants

fun NavGraphBuilder.otherScreenComposable() {
    val route = "${Constants.OTHER_SCREEN}/{${Constants.OTHER_SCREEN_ARGUMENT}}"
    composable(
        route = route,
        arguments = listOf(navArgument(Constants.OTHER_SCREEN_ARGUMENT) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        when (navBackStackEntry.arguments!!.getString(Constants.OTHER_SCREEN_ARGUMENT)) {
            Constants.SINGLE_API_CALL_SCREEN -> {
                SingleApiCallScreen()
            }
            Constants.SERIES_API_CALL_SCREEN -> {
                SeriesApiCallScreen()
            }
            Constants.PARALLEL_API_CALL_SCREEN -> {
                ParallelApiCallScreen()
            }
            Constants.ROOM_DB_SCREEN -> {
                RoomScreen()
            }
            Constants.TRY_CATCH_EXCEPTION_HANDLING_SCREEN -> {
                TryCatchScreen()
            }
            Constants.EXCEPTION_HANDLER_EXCEPTION_HANDLING_SCREEN -> {
                ExceptionHandlerScreen()
            }
            Constants.SUPERVISOR_EXCEPTION_HANDLING_SCREEN -> {
                SupervisorScreen()
            }
            Constants.TIMEOUT_SCREEN -> {
                TimeoutScreen()
            }
            Constants.ONE_LONG_TASK_SCREEN -> {
                OneLongTaskScreen()
            }
            Constants.TWO_LONG_TASK_SCREEN -> {
                TwoLongTaskScreen()
            }
        }
    }
}