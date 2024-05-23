package com.example.watermonitorapp.myUi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.watermonitorapp.R
import com.example.watermonitorapp.TabItem
import com.example.watermonitorapp.ui.theme.OceanColor
import com.example.watermonitorapp.ui.theme.White
import com.example.watermonitorapp.vm.WaterAppViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenUi(viewModel: WaterAppViewModel) {


    val waterControlItem by viewModel.waterControlItem.collectAsState()
    val userParamsItem by viewModel.userParamsItem.collectAsState()

    val tabItems = listOf(
        TabItem(
            "",
            R.drawable.main_screen_waterdrop_ic
        ),
        TabItem(
            "",
            R.drawable.main_screen_profile_ic
        )
    )
    val selectedItemIndex = remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedItemIndex.intValue) {
        pagerState.animateScrollToPage(selectedItemIndex.intValue)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedItemIndex.intValue = pagerState.currentPage
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedItemIndex.intValue,
                containerColor = OceanColor,
                contentColor = White,
                indicator = { tabPositions ->
                    if (selectedItemIndex.intValue < tabPositions.size) {
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedItemIndex.intValue]),
                            height = 3.dp,
                            color = White
                        )
                    }
                }
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedItemIndex.intValue,
                        onClick = {
                            selectedItemIndex.intValue = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.itemIcon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.height(80.dp)
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                when (index) {
                    0 -> WaterAppHomeScreen(waterControlItem, viewModel)
                    1 -> WaterAppProfileScreen(userParamsItem, viewModel)
                }
            }
        }
    }
}