package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

// 테이블 설정 화면
@Composable
fun TableSettingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(36.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 뒤로가기 버튼/아이콘
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "뒤로가기 아이콘",
                    modifier = Modifier.size(40.dp)
                )

                // 테이블 관리 텍스트
                Text(
                    text = "테이블 관리",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )

                // 빈 공간 채우기
                Spacer(modifier = Modifier.weight(1f))

                // 열과 행 설정 버튼
                Button(
                    onClick = {},
                    shape = MaterialTheme.shapes.medium
                ) {
                    // 아이콘
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "열과 행 설정 아이콘"
                    )

                    // 텍스트
                    Text(
                        text = "열과 행 설정",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // 테이블 리스트
            TableSettingGridList()

        }
    }
}

// 테이블 설정 그리드 리스트
@Composable
fun TableSettingGridList() {
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 20.dp),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(12) {
            TableSettingItem()
        }
    }
}

// 테이블 설정 아이템
@Composable
fun TableSettingItem() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxWidth()
            .height(288.dp)
            .padding(top = 16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            border = CardDefaults.outlinedCardBorder().copy(2.dp),
            shape = MaterialTheme.shapes.medium // 10dp
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp, 12.dp, 18.dp, 9.dp)
                    .fillMaxSize()
            ) {
                // 테이블 번호
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        // 테이블 번호
                        text = "01",
                        fontSize = 24.sp,
                    )
                }

                // Divider
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp),
                    thickness = 2.dp
                )
            }
        }

        // 삭제 뱃지
        Badge(
            modifier = Modifier
                .size(30.dp)
                .offset(x = 12.dp, y = (-12).dp),
            containerColor = Color.Green
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "삭제 아이콘",
            )
        }
    }

}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=1551dp,height=1053dp,dpi=160",
)
@Composable
fun PreviewTableManageScreen() {
    DaehakjumakTheme {
        TableSettingScreen()
    }
}