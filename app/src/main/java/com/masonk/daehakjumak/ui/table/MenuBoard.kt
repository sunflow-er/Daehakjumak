package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong

// 메뉴판
@Composable
fun MenuBoard() {
    var selectedTabIndex by remember { mutableStateOf(0) } // 선택된 탭 인덱스
    val tabList = listOf("주메뉴", "음료") // 탭 목록

    // 메뉴판
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(32.dp, 32.dp, 32.dp, 0.dp)
    ) {
        // 가로탭 레이아웃
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = BackgroundNormal,
            contentColor = LabelStrong,
            modifier = Modifier
                .width(320.dp),
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = LabelStrong
                )
            }
        ) {
            tabList.forEachIndexed { index, title ->
                // 탭
                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                )
            }
        }

        // 주메뉴/음료 리스트 화면
        when (selectedTabIndex) {
            0 -> {
                MenuGridList() // 주메뉴
            }

            1 -> {
                MenuGridList() // 음료
            }
        }
    }
}

// 메뉴 리스트
@Composable
fun MenuGridList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(12) {
            MenuItem()
        }
    }
}

// 메뉴 아이템
@Composable
fun MenuItem() {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(360.dp),
        border = BorderStroke(2.dp, LabelNormal),
        colors = CardDefaults.cardColors(containerColor = LabelNormal2, contentColor = LabelBlack)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(color = LabelNeutral)
            ) {
                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "메뉴 사진",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = "제육볶음",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 30.dp)
            )
            Text(
                text = "10,000원",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
            )

        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1069dp,height=892dp,dpi=160",
)
@Composable
fun PreviewMenuBoard() {
    DaehakjumakTheme {
        MenuBoard()
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=324dp,height=360dp,dpi=160",
)
@Composable
fun previewMenuItem() {
    DaehakjumakTheme {
        MenuItem()
    }
}