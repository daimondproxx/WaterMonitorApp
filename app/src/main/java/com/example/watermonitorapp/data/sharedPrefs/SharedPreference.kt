package com.example.watermonitorapp.data.sharedPrefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("isFirstLaunch", true)
    }

    fun setFirstLaunch(isFirstLaunch: Boolean) {
        sharedPreferences.edit().putBoolean("isFirstLaunch", isFirstLaunch).apply()
    }
}