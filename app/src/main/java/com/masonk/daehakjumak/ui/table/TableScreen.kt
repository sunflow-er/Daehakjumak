package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

// 테이블 화면
@Composable
fun TableScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // 앱 로고와 주막 이름
        LogoWithTitle()

        // 테이블 리스트
        TableGridList()
    }
}

// 앱 로고와 주막 이름
@Composable
fun LogoWithTitle() {
    Row(
        modifier = Modifier.padding(start = 35.dp, top = 39.dp),
        verticalAlignment = Alignment.CenterVertically // 수직 중앙 정렬
    ) {
        // 앱 로고
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "App Logo",
            modifier = Modifier.size(50.dp)
        )

        // 주막 이름
        Text(
            text = "대학주막",
            fontSize = 32.sp,
            modifier = Modifier.padding(start = 14.dp)
        )
    }
}

// 테이블 리스트
@Composable
fun TableGridList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // 4열
        modifier = Modifier.fillMaxSize().padding((35-8).dp, (32-8).dp), // 테이블카드 패딩 고려(-8)
    ) {
        items(12) { index ->
            // 테이블
            TableCard()
        }
    }
}

// 테이블 카드
@Composable
fun TableCard() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        TableDialog(onDismiss = { showDialog = false })
    }

    Card(
        border = CardDefaults.outlinedCardBorder().copy(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(288.dp)
            .padding(8.dp) // 상하좌우 8dp 패딩 적용
            .clickable(enabled = true) { showDialog = true },
        shape = MaterialTheme.shapes.medium // 10dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp, 12.dp, 18.dp, 9.dp)
        ) {
            // 테이블 번호, 비우기 버튼
            Row(modifier = Modifier.fillMaxWidth()) {
                Text( // 테이블 번호
                    text = "01",
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text( // 비우기 버튼
                    text = "비우기",
                    fontSize = 20.sp,
                )
            }

            // Divider
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp),
                thickness = 2.dp
            )

            // 테이블 주문 리스트
            TableOrderList()
        }
    }
}

// 테이블 주문 리스트
@Composable
fun TableOrderList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {
        items(10) {
            // 테이블 주문
            TableOrderChip()
        }
    }
}

// 테이블 주문 칩
@Composable
fun TableOrderChip() {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(bottom = 8.dp),
        color = Color.LightGray,
        contentColor = Color.Black
    ) {
        Row(modifier = Modifier.padding(8.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            // 메뉴 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "아이콘",
                // modifier = Modifier.size(20.dp, 20.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            // 메뉴 이름
            Text(
                text = "메뉴 이름",
                fontSize = 16.sp
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    device = "spec:width=1685dp,height=1053dp,dpi=160",
)
@Composable
fun previewTableScreen() {
    DaehakjumakTheme {
        TableScreen()
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=358dp,height=288dp,dpi=160",
)
@Composable
fun previewTableCard() {
    DaehakjumakTheme {
        TableCard()
    }
}