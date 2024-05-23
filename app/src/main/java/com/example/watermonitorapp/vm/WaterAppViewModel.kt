package com.example.watermonitorapp.vm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watermonitorapp.data.entity.UserParams
import com.example.watermonitorapp.data.entity.WaterControl
import com.example.watermonitorapp.data.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class WaterAppViewModel(private val repository: Repository) : ViewModel() {

    private val _waterControlItem = MutableStateFlow<WaterControl?>(null)
    val waterControlItem= _waterControlItem.asStateFlow()
    private val _userParamsItem = MutableStateFlow<UserParams?>(null)
    val userParamsItem= _userParamsItem.asStateFlow()
    val waterAmount = mutableIntStateOf(200)

    init {
        viewModelScope.launch {
            repository.getWaterItemById(1).collect {
                _waterControlItem.value = it
            }
        }
        viewModelScope.launch {
            repository.getUserParamsById(1).collect {
                _userParamsItem.value = it
            }
        }
        checkAndResetOldProgress()
    }



    // Сомнительно, нооо OKAY
    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAndResetOldProgress() {
        viewModelScope.launch {
            repository.getAllWaterData().collect {
                if (it.isNotEmpty()) {
                    val currentDate = LocalDate.now().toEpochDay()
                    val currentWaterItem = waterControlItem.value
                    if (currentWaterItem != null) {
                        if (currentWaterItem.date < currentDate) {
                            val resetProgressWaterControl =
                                currentWaterItem.copy(date = currentDate, currentWater = 0)
                            repository.insertWaterItem(resetProgressWaterControl)
                        }
                    }
                }
            }
        }
    }


    fun firstInsertUserParams(weight: Int, gender: Boolean) {
        viewModelScope.launch {
            repository.getAllUserParams().collect{ userParams ->
                if (userParams.isEmpty()) {
                    repository.insertUserParams(UserParams(
                        gender = gender,
                        weight = weight))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun firstInsertWaterControl(weight: Int, gender: Boolean){
        viewModelScope.launch {
            repository.getAllWaterData().collect {
                if (it.isEmpty()) {
                    val totalWater = if (gender) weight*35 else weight*31
                    repository.insertWaterItem(WaterControl(
                        totalWater = totalWater,
                        currentWater = 0,
                        date = LocalDate.now().toEpochDay()
                    ))
                }
            }
        }
    }

    fun addWaterItem(amount: Int) {
        viewModelScope.launch {
            val currentWaterControl = _waterControlItem.value
            if (currentWaterControl != null) {
                val newWaterControl = currentWaterControl.copy(currentWater = currentWaterControl.currentWater + amount)
                repository.insertWaterItem(newWaterControl)
            }
        }
    }

    fun updateUserWeight(weight: Int) {
        viewModelScope.launch {
            val currentUserWeightParams = _userParamsItem.value
            val currentWaterControl = _waterControlItem.value
            if (currentUserWeightParams != null) {
                val newUserWeightParams = currentUserWeightParams.copy(weight = weight)
                val newTotalWater = if (currentUserWeightParams.gender) weight*35 else weight*31
                repository.insertUserParams(newUserWeightParams)

                if (currentWaterControl != null){
                    val updatedCurrentWaterByWeight = currentWaterControl.copy(totalWater = newTotalWater)
                    repository.insertWaterItem(updatedCurrentWaterByWeight)
                }
            }
        }
    }

    fun updateUserGender(gender: Boolean) {
        viewModelScope.launch {
            val currentUserGender = _userParamsItem.value
            if (currentUserGender != null) {
                val newUserGender = currentUserGender.copy(
                    gender = gender
                )
                repository.insertUserParams(newUserGender)
            }
        }
    }

    fun addWaterAmount() {
        waterAmount.intValue += 25
    }
    fun minusWaterAmount() {
        waterAmount.intValue -= 25
    }

    fun isFirstLaunch(): Boolean {
        return repository.isFirstLaunch()
    }

    fun setFirstLaunch(isFirstLaunch: Boolean) {
        repository.setFirstLaunch(isFirstLaunch)
    }
}