package com.example.watermonitorapp.data.repository

import android.content.SharedPreferences
import com.example.watermonitorapp.data.db.AppDatabase
import com.example.watermonitorapp.data.entity.UserParams
import com.example.watermonitorapp.data.entity.WaterControl
import com.example.watermonitorapp.data.sharedPrefs.SharedPreference

class Repository(private val db: AppDatabase, private val sharedPreferences: SharedPreference) {

    suspend fun insertWaterItem(waterControl: WaterControl) {
        db.waterControlDao().insertWaterItem(waterControl)
    }

    fun getWaterItemById(id: Int) = db.waterControlDao().getWaterItemById(id)

    fun getAllWaterData() = db.waterControlDao().getAllWaterItems()

    suspend fun insertUserParams(userParams: UserParams) {
        db.userParamsDao().insertUserParams(userParams)
    }

    fun getUserParamsById(id: Int) = db.userParamsDao().getUserParamsById(id)

    fun getAllUserParams() = db.userParamsDao().getAllUserParams()

    suspend fun getUser() = db.userParamsDao().getUser()

    fun isFirstLaunch() = sharedPreferences.isFirstLaunch()

    fun setFirstLaunch(isFirstLaunch: Boolean) = sharedPreferences.setFirstLaunch(isFirstLaunch)
}
