package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelNormal

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
                .padding(80.dp)
                .fillMaxSize(),
            shape = MaterialTheme.shapes.large // 20dp
        ) {
            // 주문 화면
            TableOrderScreen()

            // 결제QR 화면
            //TableQRScreen()

            // 주문 및 결제 완료 화면
            //TableCompleteScreen()
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
            thickness = 2.dp,
            color = LabelNormal
        )

        // 장바구니
        Box(modifier = Modifier.weight(1f)) {
            Basket()
        }
    }
}

// 테이블 결제 QR코드 화면
@Composable
fun TableQRScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 33.dp, 40.dp, 0.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "창닫기 아이콘",
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = "QR코드 결제",
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 25.dp)
        )
        Text(
            text = "QR코드를 스캔하여 결제를 완료해주세요.",
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 21.dp)
        )

        Card(
            modifier = Modifier
                .padding(top = 49.dp)
                .size(460.dp, 555.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "QR코드 사진",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = "합계 14,000원",
                    fontSize = 44.sp,
                    modifier = Modifier.padding(top = 34.dp)
                )
            }
        }

    }
}

// 테이블 결제 완료 화면
@Composable
fun TableCompleteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 33.dp, 40.dp, 0.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "창닫기 아이콘",
                modifier = Modifier.size(28.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 186.dp)
                .size(300.dp)
                .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "결제 완료 아이콘/로고",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(text = "결제가 완료되었습니다.",
            fontSize = 40.sp,
            modifier = Modifier.padding(43.dp))

    }
}


@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1551dp,height=1053dp,dpi=160",
)
@Composable
fun previewTableDialog() {
    DaehakjumakTheme {
        TableDialog(onDismiss = {})
    }
}