package com.example.watermonitorapp.myUi

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitorapp.R
import com.example.watermonitorapp.data.entity.UserParams
import com.example.watermonitorapp.myUi.uiResources.PressableImageButton
import com.example.watermonitorapp.ui.theme.OceanColor
import com.example.watermonitorapp.vm.WaterAppViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterAppProfileScreen(userParams: UserParams?,viewModel: WaterAppViewModel) {

    val context = LocalContext.current

    var userWeight by remember {
        mutableStateOf("${userParams?.weight ?: 0}")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Выберите Ваш пол",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            PressableImageButton(
                onClick = {
                    viewModel.updateUserGender(true)
                },
                imageResource = R.drawable.chose_male_ic,
                selected = if (userParams != null) userParams.gender else  false
            )

            PressableImageButton(
                onClick = {
//                    isSelected.value = !isSelected.value
//                    viewModel.changedSelection()
                    viewModel.updateUserGender(false)
                },
                imageResource = R.drawable.chose_female_ic,
                selected = if (userParams != null) !userParams.gender else  false
            )
        }
        Text(
            text = "Выберите Ваш вес",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
            .padding(top = 50.dp, bottom = 50.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(OceanColor)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.padding(start = 25.dp)) {
                    TextField(
                        value = userWeight,
                        onValueChange = { userWeight = it },
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
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if (userWeight == "") {
                    Toast.makeText(context, "Неверно введен вес, попробуйте еще раз", Toast.LENGTH_LONG).show()
                } else viewModel.updateUserWeight(userWeight.toInt())
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = OceanColor
            )
        ) {
            Text(
                text = "Save",
                color = Color.White
            )
        }

    }
}