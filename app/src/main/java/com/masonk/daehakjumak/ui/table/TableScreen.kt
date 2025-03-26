package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.StatusServed

// 테이블 화면
@Composable
fun TableScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(36.dp)
    ) {
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
        verticalAlignment = Alignment.CenterVertically // 수직 중앙 정렬
    ) {
        // 앱 로고
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "App Logo",
            modifier = Modifier.size(50.dp),
            tint = Color.White
        )

        // 주막 이름
        Text(
            text = "주막이름",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(start = 14.dp),
            color = LabelStrong
        )
    }
}

// 테이블 리스트
@Composable
fun TableGridList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // 4열
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(12) { index ->
            // 테이블
            TableItem()
        }
    }
}

// 테이블 카드
@Composable
fun TableItem() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        TableDialog(onDismiss = { showDialog = false })
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LabelNeutral,
            contentColor = StatusServed
        ),
        border = BorderStroke(2.dp, LabelNormal2),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(288.dp)
            .clickable(enabled = true) { showDialog = true },
        shape = MaterialTheme.shapes.medium // 10dp
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp, 12.dp, 18.dp, 18.dp)
                .fillMaxSize()
        ) {
            // 테이블 번호, 비우기 버튼
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    // 테이블 번호
                    text = "01",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text( // 비우기 버튼
                    text = "비우기",
                    style = MaterialTheme.typography.labelLarge

                )
            }

            Spacer(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth()
                    .height(2.dp)
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
            .padding(top = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            // 테이블 주문
            TableOrderItem()
        }
    }
}

// 테이블 주문 칩
@Composable
fun TableOrderItem() {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = StatusServed,
        contentColor = LabelBlack
    ) {
        Row(
            modifier = Modifier.padding(8.dp, 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 메뉴 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "아이콘",
                // modifier = Modifier.size(20.dp, 20.dp)
            )

            // 메뉴 이름
            Text(
                text = "메뉴 이름",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    device = "spec:width=1551dp,height=1053dp,dpi=160",
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
        TableItem()
    }
}