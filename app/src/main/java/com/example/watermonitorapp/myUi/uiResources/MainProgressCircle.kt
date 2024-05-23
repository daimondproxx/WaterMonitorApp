package com.example.watermonitorapp.myUi.uiResources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitorapp.R
import com.example.watermonitorapp.data.entity.WaterControl
import com.example.watermonitorapp.ui.theme.OceanColor
import com.example.watermonitorapp.ui.theme.robotoFontFamily
import com.example.watermonitorapp.vm.WaterAppViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainProgressCircle(waterControlItem: WaterControl?, viewModel: WaterAppViewModel) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        HalfCircleProgressBar(
            waterControlItem?.currentWater?.div(waterControlItem.totalWater.toFloat()) ?: 0.0f,
            radius = 170.dp,
        )
        Box(
            modifier = Modifier
                .size(285.dp)
        ) {
            ThirdDimCircle(innerSize = 284.dp, outerSize = 285.dp)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                    Text(
                        text = "${waterControlItem?.currentWater ?: 0}/${waterControlItem?.totalWater ?: 0}ml",
                        fontSize = 32.sp,
                        fontFamily = robotoFontFamily,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                Text(
                    text = "Ежедневный прогресс",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            ) {
                IconButton(
                    onClick = {
                        viewModel.addWaterItem(viewModel.waterAmount.intValue)
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_water_ic),
                        contentDescription = null
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align((Alignment.BottomCenter))
                        .padding(bottom = 10.dp)
                ) {
                    IconButton(
                        onClick = {
                        viewModel.minusWaterAmount()
                    },
                        modifier = Modifier.size(18.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus_circle_ic),
                            contentDescription = "minus water amount",
                            tint = OceanColor
                        )
                    }

                    Text(
                        text = "${viewModel.waterAmount.intValue}",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )

                    IconButton(onClick = {
                        viewModel.addWaterAmount()
                    },
                        modifier = Modifier.size(18.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle_ic),
                            contentDescription = "add water amount",
                            tint = OceanColor
                        )
                    }
                }

            }
        }
    }
}