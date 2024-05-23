package com.example.watermonitorapp.myUi.entryScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitorapp.R
import com.example.watermonitorapp.myUi.uiResources.PressableImageButton
import com.example.watermonitorapp.ui.theme.OceanColor
import com.example.watermonitorapp.vm.WaterAppViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FirstEntryScreen(viewModel: WaterAppViewModel, onClick: () -> Unit) {

    val isMaleSelected = remember { mutableStateOf(true) }
    val userWeight = remember { mutableStateOf("50") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Выберите Ваш пол",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 100.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 50.dp)
        ) {
            PressableImageButton(
                onClick = {
                    isMaleSelected.value = !isMaleSelected.value
                    Log.d("ButtonTag", "Male Button Clicked")
                },
                enabled = !isMaleSelected.value,
                imageResource = R.drawable.chose_male_ic,
                selected = isMaleSelected.value
            )
            PressableImageButton(
                onClick = {
                    isMaleSelected.value = !isMaleSelected.value
                    Log.d("ButtonTag", "Female Button Clicked")
                },
                enabled = isMaleSelected.value,
                imageResource = R.drawable.chose_female_ic,
                selected = !isMaleSelected.value
            )
        }

        Text(
            text = "Выберите Ваш вес",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(OceanColor)
        ) {
            Box(modifier = Modifier.padding(start = 25.dp)) {
                TextField(
                    value = userWeight.value,
                    onValueChange = { userWeight.value = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .width(150.dp)
                        .height(75.dp)
                )
            }
            Text(
                text = "KG",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Button(
            onClick = {
                viewModel.firstInsertUserParams(userWeight.value.toIntOrNull() ?: 0, isMaleSelected.value)
                viewModel.firstInsertWaterControl(userWeight.value.toIntOrNull() ?: 0, isMaleSelected.value)
                viewModel.setFirstLaunch(false)
                onClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = OceanColor),
            modifier = Modifier.padding(top = 120.dp)
        ) {
            Text(text = "Готово")
        }

    }
}