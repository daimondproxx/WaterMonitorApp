package com.example.watermonitorapp.myUi.uiResources

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ThirdDimCircle(
    innerSize: Dp,
    outerSize: Dp
) {
    Canvas(
        modifier = Modifier
            .size(outerSize)
    ) {
        drawArc(
            color = Color.LightGray,
            -90f,
            360f,
            useCenter = false,
            style = Stroke(4.dp.toPx(), cap = StrokeCap.Round),
            alpha = 0.3f
        )
    }
    Canvas(
        modifier = Modifier
            .size(innerSize)
    ) {
        drawArc(
            color = Color.LightGray,
            -90f,
            360f,
            useCenter = false,
            style = Stroke(1.dp.toPx(), cap = StrokeCap.Round),
            alpha = 0.5f
        )
    }
}