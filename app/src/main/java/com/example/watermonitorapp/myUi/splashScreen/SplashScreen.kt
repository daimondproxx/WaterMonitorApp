package com.example.watermonitorapp.myUi.splashScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.watermonitorapp.R
import com.example.watermonitorapp.navigation.Screen
import com.example.watermonitorapp.vm.WaterAppViewModel
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(navController: NavHostController, viewModel: WaterAppViewModel) {
    val isPlaying = remember {
        mutableStateOf(false)
    }
    val composition = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.splash_screen_bottle_anim)
    )
    val animationProgress = animateLottieCompositionAsState(
        composition = composition.value,
        isPlaying = isPlaying.value
    )
    LaunchedEffect(true) {
        isPlaying.value = true
        delay(4000)
        if (viewModel.isFirstLaunch()) {
            navController.popBackStack()
            navController.navigate(Screen.EntryScreen.route)
        } else {
            navController.popBackStack()
            navController.navigate(Screen.MainScreen.route)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            modifier = Modifier.size(300.dp),
            composition = composition.value,
            progress = { animationProgress.value },
        )
    }
}

