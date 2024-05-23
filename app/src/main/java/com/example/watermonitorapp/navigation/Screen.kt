package com.example.watermonitorapp.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object EntryScreen : Screen("entry_screen")
    object MainScreen : Screen("main_screen")
}