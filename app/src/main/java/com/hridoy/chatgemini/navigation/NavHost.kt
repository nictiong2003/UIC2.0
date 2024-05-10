package com.hridoy.chatgemini.navigation

import androidx.activity.compose.BackHandler
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hridoy.chatgemini.presentation.screens.HomeScreen
import com.hridoy.chatgemini.presentation.screens.ViewScreen
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAnimationNavHost(
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
    uriState: MutableStateFlow<String>,
    navController: NavHostController,
    startDestination: String = ScreenDestinations.HomeScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        screen(ScreenDestinations.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        screen(ScreenDestinations.ViewScreen.route) {
            ViewScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.HomeScreen.route)
                },
                imagePicker = imagePicker,
                uriState = uriState,
            )
        }
    }
    // Back Handler
    BackHandler {
        navController.popBackStack()
    }
}
