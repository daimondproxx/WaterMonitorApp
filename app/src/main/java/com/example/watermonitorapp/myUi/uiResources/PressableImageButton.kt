package com.example.watermonitorapp.myUi.uiResources

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource

@Composable
fun PressableImageButton(
    onClick: () -> Unit,
    imageResource: Int,
    selected: Boolean,
    enabled: Boolean = true
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        alpha = if (selected) 1f else 0.8f,
        colorFilter = if (!selected) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.7f) }) else null,
        modifier = Modifier
            .noRippleClickable(onClick = onClick, enabled)
    )
}

fun Modifier.noRippleClickable(onClick: () -> Unit, enabled: Boolean): Modifier = this.then(
    if (enabled) {
        Modifier.pointerInput(Unit) {
            while (true) {
                awaitPointerEventScope {
                    awaitFirstDown()
                    val event = awaitPointerEvent()
                    if (event.changes.all { it.changedToUp() }) {
                        onClick()
                    }
                }
            }
        }
    } else {
        this
    }
)