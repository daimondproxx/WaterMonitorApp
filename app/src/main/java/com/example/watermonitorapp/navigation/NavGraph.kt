package com.example.watermonitorapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.watermonitorapp.myUi.MainScreenUi
import com.example.watermonitorapp.myUi.entryScreen.FirstEntryScreen
import com.example.watermonitorapp.myUi.splashScreen.SplashScreen
import com.example.watermonitorapp.vm.WaterAppViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(navController: NavHostController, viewModel: WaterAppViewModel) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController, viewModel)
        }
        composable(route = Screen.EntryScreen.route) {
            FirstEntryScreen(viewModel = viewModel) {
                navController.popBackStack()
                navController.navigate(Screen.MainScreen.route)
            }
        }
        composable(route = Screen.MainScreen.route) {
            MainScreenUi(viewModel = viewModel)
        }
    }

}