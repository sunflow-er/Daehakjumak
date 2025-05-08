package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.LineStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun MenuPriceEditDialog() {
    val keys = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8 ,9),
        listOf(-2, 0, -1) // -2: Reset, -1: delete
    )

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier.size(742.dp, 892.dp),
            colors = CardDefaults.cardColors(
                containerColor = LabelNeutral,
                contentColor = LabelStrong
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // X 아이콘
                Row(modifier = Modifier
                    .padding(32.dp, 32.dp, 32.dp, 0.dp)
                    .fillMaxWidth()) {
                    // 빈공간
                    Spacer(modifier = Modifier.weight(1f))
                    
                    // 다이얼로그 닫기
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "다이얼로그 닫기 아이콘",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                        tint = LabelNormal
                    )
                }

                // 설명
                Text(
                    text = "금액을 입력해주세요.",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // 금액
                TextField(
                    value = "0원",
                    onValueChange = {},
                    singleLine = true,
                    enabled = false,
                    modifier = Modifier.padding(top= 34.dp)
                )

                // 가로선
                HorizontalDivider(
                    modifier = Modifier.padding(top = 63.dp).fillMaxWidth(),
                    thickness = 2.dp,
                    color = LineStrong,
                )

                // 숫자 키패드
                keys.forEachIndexed { rowIndex, row ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        row.forEachIndexed { colIndex, key ->
                            Box(
                                modifier = Modifier.height(123.dp).weight(1f).clickable {
                                    // TODO clickable
                                },
                                contentAlignment = Alignment.Center,
                            ) {
                                when (key) {
                                    -2 -> { // 초기화, 금액 정보 삭제
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "초기화 아이콘",
                                            modifier = Modifier
                                                .size(24.dp),
                                            tint = LabelNormal
                                        )
                                    }
                                    -1 -> { // 지우기, 숫자 하나씩 제거
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "지우기 아이콘",
                                            modifier = Modifier
                                                .size(24.dp),
                                            tint = LabelNormal
                                        )
                                    }
                                    else -> { // 숫자
                                        Text(
                                            text = key.toString(),
                                            style = MaterialTheme.typography.displayLarge,
                                        )
                                    }
                                }

                            }
                            
                            // 세로선
                            if (colIndex != row.lastIndex) {
                                VerticalDivider(
                                    modifier = Modifier.fillMaxHeight(),
                                    thickness = 2.dp,
                                    color = LineStrong
                                )
                            }
                        }

                        // 가로선
                        if (rowIndex != keys.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(),
                                thickness = 2.dp,
                                color = LineStrong
                            )
                        }
                    }
                }

                // 다음 버튼
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryNormal,
                        contentColor = LabelBlack
                    ),
                ) {
                    Text(
                        text = "다음",
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
        }
    }
}

