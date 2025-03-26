package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.ui.theme.BackgroundElevated
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled
import com.masonk.daehakjumak.ui.theme.LabelDisabled2
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

// 장바구니
@Composable
fun Basket() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundElevated)
    ) {
        // Head
        Row(
            modifier = Modifier
                .weight(92f)
                .padding(32.dp, 28.dp)
                .fillMaxWidth(),
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
                style = MaterialTheme.typography.headlineMedium,
                color = LabelStrong,
                modifier = Modifier.padding(start = 16.dp)
            )

            // 빈 공간
            Spacer(modifier = Modifier.weight(1f))

            // 테이블 번호
            Surface(
                modifier = Modifier.size(38.dp, 36.dp),
                shape = MaterialTheme.shapes.medium,
                color = PrimaryNormal,
                contentColor = LabelNeutral
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "01",
                        style = MaterialTheme.typography.labelLarge,
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
            thickness = 2.dp,
            color = LabelNeutral
        )

        // 장바구니 리스트
        Box(
            modifier = Modifier
                .weight(606f)
                .fillMaxWidth()

        ) {
            BasketList()
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = LabelNeutral
        )

        // Tail
        Column(
            modifier = Modifier
                .weight(198f)
                .padding(32.dp, 21.dp)
                .fillMaxWidth()

        ) {
            // 선택 메뉴의 종류와 수, 금액에 대한 정보
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 메뉴 종류와 총수량
                Text(
                    text = "0가지 메뉴 0개",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LabelStrong,
                    modifier = Modifier.weight(1f)
                )

                // Divider
                VerticalDivider(
                    modifier = Modifier
                        .height(34.dp)
                        .padding(16.dp, 0.dp),
                    thickness = 2.dp,
                    color = LabelStrong
                )

                // 합계 금액
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 합계 텍스트
                    Text(
                        text = "합계",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LabelStrong,
                    )

                    // 빈 공간
                    Spacer(modifier = Modifier.weight(1f))

                    // 금액
                    Text(
                        text = "10,000원",
                        style = MaterialTheme.typography.headlineMedium,
                        color = LabelStrong
                    )
                }
            }

            // 결제하기 버튼
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryNormal,
                    contentColor = LabelBlack
                ),
            ) {
                Text(
                    text = "결제하기",
                    style = MaterialTheme.typography.headlineSmall
                )
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

            // Divider
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = LabelNeutral
            )
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
            .padding(28.dp, 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 메뉴 이름
            Text(
                text = "메뉴 이름",
                style = MaterialTheme.typography.headlineLarge,
                color = LabelStrong
            )

            // 빈 공간
            Spacer(modifier = Modifier.weight(1f))

            // 삭제 버튼
            Surface(
                modifier = Modifier.size(67.dp, 44.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(2.dp, LabelStrong),
                color = LabelNeutral,
                contentColor = LabelStrong
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "삭제",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(16.dp, 10.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "9,000원",
                style = MaterialTheme.typography.headlineMedium,
                color = LabelStrong
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // - 버튼
            Surface(
                modifier = Modifier.size(104.dp, 64.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LabelDisabled2),
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
                style = MaterialTheme.typography.headlineMedium,
                color = LabelStrong,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            // + 버튼
            Surface(
                modifier = Modifier.size(104.dp, 64.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LabelNormal2),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "+아이콘",
                    )
                }
            }
        }

    }


}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=487dp,height=896dp,dpi=160",
)
@Composable
fun previewBasket() {
    DaehakjumakTheme {
        Basket()
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
        BasketList()
    }
}

