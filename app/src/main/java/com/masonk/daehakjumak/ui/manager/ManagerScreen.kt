package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNeutral2
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun ManagerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(36.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 주막 이름 텍스트
            Text(
                text = "주막이름",
                style = MaterialTheme.typography.displayLarge,
                color = LabelStrong
            )

            // 주막 이름 수정 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "주막이름 수정 아이콘",
                modifier = Modifier.size(40.dp)
            )
        }

        // 매출 관리
        Text(
            text = "매출 관리",
            style = MaterialTheme.typography.labelLarge,
            color = LabelStrong,
            modifier = Modifier.padding(top = 26.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(476.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 오픈/마감 버튼
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(250f)
                    .fillMaxWidth()
            ) {
                // 오픈 버튼
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    color = PrimaryNormal,
                    contentColor = BackgroundNormal
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "오픈",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .align(Alignment.Start)
                        )
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "오픈 아이콘",
                            modifier = Modifier
                                .size(140.dp)
                                .align(Alignment.End)
                        )
                    }
                }

                // 마감 버튼
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    color = LabelNeutral,
                    contentColor = LabelDisabled
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "마감",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .align(Alignment.Start)
                        )
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "마감 아이콘",
                            modifier = Modifier
                                .size(140.dp)
                                .align(Alignment.End)
                        )
                    }
                }

            }

            // 오늘의 매출
            Card(
                modifier = Modifier
                    .weight(600f)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(contentColor = LabelNeutral2)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    // 오늘의 매출 텍스트
                    Box(
                        modifier = Modifier
                            .size(120.dp, 36.dp)
                            .background(
                                color = LabelDisabled,
                                shape = MaterialTheme.shapes.extraSmall
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "오늘의 매출",
                            style = MaterialTheme.typography.labelLarge,
                            color = LabelBlack
                        )
                    }

                    // 매출 금액 텍스트
                    Text(
                        text = "560,000원",
                        style = MaterialTheme.typography.displayLarge,
                        color = PrimaryNormal,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    // Divider
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxWidth(),
                        thickness = 1.dp
                    )

                    // 메뉴별 매출 텍스트
                    Text(
                        text = "메뉴별 매출",
                        style = MaterialTheme.typography.labelLarge,
                        color = LabelStrong,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)
                    )

                    // 메뉴별 매출
                    LazyColumn(modifier = Modifier.padding(20.dp, 26.dp, 20.dp, 20.dp)) {
                        items(12) { index ->
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                val tag = when (index) {
                                    0 -> "주메뉴"
                                    2 -> "음료"
                                    else -> ""
                                }

                                Text(
                                    text = tag,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PrimaryNormal,
                                    modifier = Modifier.weight(58f)
                                )

                                Text(
                                    text = "메뉴 이름",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = LabelNormal2,
                                    modifier = Modifier
                                        .padding(start = 22.dp)
                                        .weight(284f)
                                )

                                Text(
                                    "100,000원",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = LabelNormal2,
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .weight(144f),
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                    }

                }
            }

            // 총 매출
            Card(
                modifier = Modifier
                    .weight(600f)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(contentColor = LabelNeutral2)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // 총 매출 텍스트
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .size(120.dp, 36.dp)
                            .background(
                                color = LabelDisabled,
                                shape = MaterialTheme.shapes.extraSmall
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "총 매출",
                            style = MaterialTheme.typography.labelLarge,
                            color = LabelBlack
                        )
                    }

                    // 총 매출 금액 텍스트
                    Text(
                        text = "560,000원",
                        style = MaterialTheme.typography.displayLarge,
                        color = PrimaryNormal,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    // Divider
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxWidth(),
                        thickness = 1.dp
                    )

                    // 메뉴별 매출 텍스트
                    Text(
                        text = "메뉴별 매출",
                        style = MaterialTheme.typography.labelLarge,
                        color = LabelStrong,
                        modifier = Modifier.padding(start = 36.dp, top = 20.dp)
                    )

                    // 메뉴별 매출
                    LazyColumn(modifier = Modifier.padding(36.dp, 26.dp, 36.dp, 14.dp)) {
                        items(12) { index ->
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .fillMaxWidth()
                            ) {

                                val tag = when (index) {
                                    0 -> "주메뉴"
                                    2 -> "음료"
                                    else -> ""
                                }

                                Text(
                                    text = tag,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PrimaryNormal,
                                    modifier = Modifier.weight(58f)
                                )

                                Text(
                                    text = "메뉴 이름",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = LabelNormal2,
                                    modifier = Modifier
                                        .padding(start = 22.dp)
                                        .weight(284f)
                                )

                                Text(
                                    "100,000원",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = LabelNormal2,
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .weight(144f)
                                )
                            }
                        }
                    }

                }
            }
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(top = 36.dp),
            thickness = 2.dp
        )

        // 주막 설정
        Text(
            text = "주막 설정",
            style = MaterialTheme.typography.labelLarge,
            color = LabelStrong,
            modifier = Modifier.padding(top = 16.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(186.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LabelNeutral,
                    contentColor = LabelStrong
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "테이블 관리",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "테이블 관리 아이콘",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(186.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LabelNeutral,
                    contentColor = LabelStrong
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "메뉴 관리",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "메뉴 관리 아이콘",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(186.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LabelNeutral,
                    contentColor = LabelStrong
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "계좌 관리",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "계좌 관리 아이콘",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "로그아웃",
                style = MaterialTheme.typography.labelLarge,
                color = LabelNormal2,
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
fun PreviewManagerScreen() {
    DaehakjumakTheme {
        ManagerScreen()
    }
}