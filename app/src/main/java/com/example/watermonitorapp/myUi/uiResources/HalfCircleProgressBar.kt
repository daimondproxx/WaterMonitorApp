package com.example.watermonitorapp.myUi.uiResources

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.watermonitorapp.R
import com.example.watermonitorapp.ui.theme.OceanColor
import com.example.watermonitorapp.ui.theme.WaterMonitorAppTheme


@Composable
fun HalfCircleProgressBar(
    percentage: Float,
    radius: Dp = 170.dp,
    mainColor: Color = OceanColor,
    secondColor: Color = Color.LightGray,
    strokeWidth: Dp = 5.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed && percentage > 0f) {
            percentage
        } else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2 + 20.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius*2)
        ) {
            Canvas(
                modifier = Modifier
                    .size(radius * 2)
            ) {
                drawArc(
                    color = secondColor,
                    -180f,
                    180f,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                )
                drawArc(
                    color = mainColor,
                    -180f,
                    if (currentPercentage.value >= 1f) 180f else 180f * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                )
            }
        }


            Box(
                modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 35.dp, start = 3.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.main_screen_waterdrop_ic),
                    contentDescription = null,
                    tint = OceanColor,
                    modifier = Modifier
                        .size(16.dp)
                        .alpha(0.6f)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 35.dp, end = 2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.main_screen_waterdrop_ic),
                    contentDescription = null,
                    tint = OceanColor,
                    modifier = Modifier
                        .size(16.dp)
                )
            }
    }
}


@Preview(showBackground = true)
@Composable
fun CirclePreview(){
    WaterMonitorAppTheme {
        HalfCircleProgressBar(percentage = 0.2f)
    }
}
