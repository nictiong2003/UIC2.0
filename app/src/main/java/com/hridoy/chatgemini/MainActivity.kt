package com.hridoy.chatgemini

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hridoy.chatgemini.common.DURATION
import com.hridoy.chatgemini.common.VALUES_X
import com.hridoy.chatgemini.common.VALUES_Y
import com.hridoy.chatgemini.common.utils.RootUtil
import com.hridoy.chatgemini.navigation.MainAnimationNavHost
import com.hridoy.chatgemini.theme.ChatGeminiTheme
import com.hridoy.chatgemini.theme.splashScreen.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private val Tag = MainActivity::class.java.simpleName
    }

    private val splashViewModel: SplashViewModel by viewModels()

    private val uriState = MutableStateFlow("")

    private val imagePicker =
        registerForActivityResult<PickVisualMediaRequest, Uri>(
            ActivityResultContracts.PickVisualMedia(),
        ) { uri ->
            uri?.let {
                uriState.update { uri.toString() }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdgeWindow()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
        )

        // Check Rooted Device
        if (RootUtil.isDeviceRooted()) {
            Timber.tag(Tag).e("onCreate - Rooted device.")
            finish()
            return
        }

        Timber.tag(Tag).d("onCreate")

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isLoading.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    VALUES_X,
                    VALUES_Y,
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = DURATION
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    VALUES_X,
                    VALUES_Y,
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = DURATION
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }
        // splashViewModel.checkStartScreen() { route -> }

        setContent {
            ConfigureTransparentSystemBars()

            ChatGeminiTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    MainAnimationNavHost(
                        imagePicker = imagePicker,
                        uriState = uriState,
                        navController = navController,
                    )
                }
            }
        }
    }


    private fun configureEdgeToEdgeWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @Composable
    private fun ConfigureTransparentSystemBars() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons,
            )

            onDispose { }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
