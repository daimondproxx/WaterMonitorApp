package com.example.watermonitorapp.myUi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.watermonitorapp.data.entity.WaterControl
import com.example.watermonitorapp.myUi.uiResources.MainProgressCircle
import com.example.watermonitorapp.vm.WaterAppViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterAppHomeScreen(waiterControlItem: WaterControl?, viewModel: WaterAppViewModel) {

    MainProgressCircle(waiterControlItem, viewModel)

}
