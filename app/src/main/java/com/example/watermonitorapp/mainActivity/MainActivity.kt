package com.example.watermonitorapp.mainActivity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.watermonitorapp.data.db.AppDatabase
import com.example.watermonitorapp.data.repository.Repository
import com.example.watermonitorapp.data.sharedPrefs.SharedPreference
import com.example.watermonitorapp.navigation.NavGraphSetup
import com.example.watermonitorapp.vm.WaterAppViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val sharedPreferences = SharedPreference(context)
            val db = AppDatabase.getDatabase(context)
            val repository = Repository(db, sharedPreferences)
            val vm = WaterAppViewModel(repository)

            NavGraphSetup(navController = navController, viewModel = vm)
        }
    }
}

