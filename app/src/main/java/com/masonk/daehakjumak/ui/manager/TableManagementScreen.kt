package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

@Composable
fun TableManagementScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(36.dp)
    ) {

        // Head
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "뒤로가기 아이콘",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "테이블 관리",
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(top = 29.dp),
            thickness = 2.dp,
            color = Color.Gray
        )


        // 테이블 관리 화면
        TablePlacementInit()
        // TablePlacement()
    }
}

@Composable
fun TablePlacementInit() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "",
            modifier = Modifier.size(377.dp)
        )
        Text(
            text = "빈 곳을 터치하여 테이블을 배치해보세요!",
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 56.dp)
        )
    }
}

@Composable
fun TablePlacement() {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}


@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=1551dp,height=1053dp,dpi=160",
)
@Composable
fun PreviewTableSettingScreen() {
    DaehakjumakTheme {
        TableManagementScreen()
    }
}