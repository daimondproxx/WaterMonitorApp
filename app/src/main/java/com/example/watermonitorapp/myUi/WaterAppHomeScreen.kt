package com.example.watermonitorapp.myUi

import androidx.compose.runtime.Composable
import com.example.watermonitorapp.data.entity.WaterControl
import com.example.watermonitorapp.myUi.uiResources.MainProgressCircle
import com.example.watermonitorapp.vm.WaterAppViewModel

@Composable
fun WaterAppHomeScreen(waiterControlItem: WaterControl?, viewModel: WaterAppViewModel) {

    MainProgressCircle(waiterControlItem, viewModel)

}
