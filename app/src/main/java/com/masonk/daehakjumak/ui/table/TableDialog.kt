package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

// 테이블 다이얼로그 화면 (주문, QR결제, 완료)
@Composable
fun TableDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false // 기본 너비 제한 해제
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(80.dp),
            shape = MaterialTheme.shapes.large // 20dp
        ) {
            // 주문 화면
            TableOrderScreen()
        }
    }
}


// 테이블 주문 화면
@Composable
fun TableOrderScreen() {
    Row(modifier = Modifier.fillMaxSize()) {
        //메뉴판
        Box(modifier = Modifier.weight(2f)) {
            MenuBoard()
        }

        // Divider
        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            thickness = 2.dp
        )

        // 장바구니
        Box(modifier = Modifier.weight(1f)) {
            Basket()
        }


    }
}

// 메뉴판
@Composable
fun MenuBoard() {
    var selectedTabIndex by remember { mutableStateOf(0) } // 선택된 탭 인덱스
    val tabList = listOf("주메뉴", "음료") // 탭 목록

    // 메뉴판
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 가로탭 레이아웃
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .width(320.dp)
                .padding(start = 32.dp, top = 32.dp)
        ) {
            tabList.forEachIndexed { index, title ->
                // 탭
                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = {
                        Text(
                            text = title,
                            fontSize = 30.sp,
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

// 장바구니
@Composable
fun Basket() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Head
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(32.dp, 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 장바구니 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "장바구니 아이콘",
                modifier = Modifier.size(36.dp)
            )

            // 장바구니 텍스트
            Text(
                text = "장바구니",
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 16.dp)
            )

            // 빈 공간
            Spacer(modifier = Modifier.weight(1f))

            // 테이블 번호
            Surface(
                modifier = Modifier.size(38.dp, 36.dp),
                shape = MaterialTheme.shapes.medium,
                color = Color.Green
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "01",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp, 4.dp)
                    )
                }
            }

            // 창닫기 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "창닫기 아이콘",
                modifier = Modifier
                    .padding(start = 32.dp)
                    .size(24.dp)
            )
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )

        // 장바구니 리스트
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(606.dp)
        ) {
            BasketList()
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )

        // Tail
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(198.dp)
        ) {
            // 선택 메뉴의 종류와 수, 금액에 대한 정보
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 21.dp, 32.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 메뉴 종류와 총수량
                Text(
                    text = "0가지 메뉴 0개",
                    fontSize = 24.sp,
                    modifier = Modifier.weight(1f)
                )

                // Divider
                VerticalDivider(
                    modifier = Modifier
                        .height(34.dp)
                        .padding(16.dp, 0.dp)
                )

                // 합계 금액
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 합계 텍스트
                    Text(
                        text = "합계",
                        fontSize = 24.sp
                    )

                    // 빈 공간
                    Spacer(modifier = Modifier.weight(1f))

                    // 금액
                    Text(
                        text = "10,000원",
                        fontSize = 30.sp
                    )
                }
            }

            // 결제하기 버튼
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp, 24.dp, 32.dp, 32.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "결제하기")
            }
        }

    }
}

// 장바구니 리스트
@Composable
fun BasketList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(6) {
            // 장바구니 아이템
            BasketItem()
        }
    }
}

// 장바구니 아이템
@Composable
fun BasketItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(246.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 36.dp, 34.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 메뉴 이름
            Text(
                text = "메뉴 이름",
                fontSize = 34.sp,
            )

            // 빈 공간
            Spacer(modifier = Modifier.weight(1f))

            // 삭제 버튼
            Surface(
                modifier = Modifier.size(67.dp, 44.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(2.dp, Color.Gray)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "삭제",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp, 10.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(34.dp, 23.dp, 34.dp, 0.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "9,000원",
                fontSize = 30.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 23.dp, 32.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // - 버튼
            Surface(
                modifier = Modifier.size(104.dp, 64.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(2.dp, Color.Gray)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "-아이콘",

                        )
                }
            }

            // 수량
            Text(
                text = "1개",
                fontSize = 30.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            // + 버튼
            Surface(
                modifier = Modifier.size(104.dp, 64.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(2.dp, Color.Gray)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "+아이콘",
                    )
                }
            }
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 28.dp, 0.dp, 0.dp),
            thickness = 2.dp
        )
    }
}

@Composable
fun MenuGridList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding((32 - 8).dp) // MenuItem 패딩 고려 (8dp)
    ) {
        items(12) {
            MenuItem()
        }
    }
}

@Composable
fun MenuItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(8.dp),
        border = CardDefaults.outlinedCardBorder().copy(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "메뉴 사진",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "제육볶음",
                    fontSize = 26.sp,
                    modifier = Modifier.padding(top = 30.dp)
                )
                Text(
                    text = "10,000원",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
                )
            }
        }
    }
}

@Composable
fun TableQRScreen() {

}

@Composable
fun TableSuccessScreen() {

}


@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1685dp,height=1053dp,dpi=160",
)
@Composable
fun previewTableDialog() {
    DaehakjumakTheme {
        TableDialog(onDismiss = {})
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=486dp,height=246dp,dpi=160",
)
@Composable
fun previewBasketItem() {
    DaehakjumakTheme {
        BasketItem()
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=324dp,height=360dp,dpi=160",
)
@Composable
fun previewMenuCard() {
    DaehakjumakTheme {
        MenuItem()
    }
}