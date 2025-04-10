package com.masonk.daehakjumak.ui.table

import android.icu.number.NumberFormatter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.core.util.formatPrice
import com.masonk.daehakjumak.presentation.model.BasketItemModel
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.viewmodel.TableDialogViewModel
import com.masonk.daehakjumak.ui.theme.BackgroundElevated
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled2
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal
import java.util.Locale

// 장바구니
@Composable
fun Basket(
    tableDialogViewModel: TableDialogViewModel,
    selectedTable: TableModel,
    onDismiss: () -> Unit,
    onClickPay: () -> Unit
) {
    var itemSortCount = 0 // 메뉴 종류 수
    var itemTotalCount = 0 // 메뉴 총 개수
    var totalPrice = 0 // 총 가격

    val basketItemList by tableDialogViewModel.basketItemList.collectAsState() // 장바구니에 담긴 메뉴 리스트
    
    // basketItemList가 업데이트 될때마다 다시 계산
    basketItemList.forEach {
        itemSortCount++
        itemTotalCount += it.count
        totalPrice += it.menuPrice * it.count
    }

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
                        text = selectedTable.number, // 테이블 번호
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
                    .clickable { onDismiss() } // 다이얼로그 닫기
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
                .fillMaxWidth(),
        ) {
            BasketList(
                basketItemList = basketItemList,
                onClickDelete = { index: Int -> tableDialogViewModel.deleteBasketItem(index) },
                onClickMinus = { index: Int -> tableDialogViewModel.decreaseBasketItemCount(index) },
                onClickPlus = { index: Int -> tableDialogViewModel.increaseBasketItemCount(index) }
            )
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
                    text = "${itemSortCount}가지 메뉴 ${itemTotalCount}개",
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
                        text = "${formatPrice(totalPrice)}원", // price 형태로 포맷, 10,000
                        style = MaterialTheme.typography.headlineMedium,
                        color = LabelStrong
                    )
                }
            }

            // 결제하기 버튼
            Button(
                onClick = onClickPay,
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
fun BasketList(
    basketItemList: List<BasketItemModel>,
    onClickDelete: (Int) -> Unit,
    onClickMinus: (Int) -> Unit,
    onClickPlus: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(basketItemList) { index, item ->
            // 장바구니 아이템
            BasketItem(
                item = item,
                onClickDelete = { onClickDelete(index) } ,
                onClickMinus = { onClickMinus(index) },
                onClickPlus = { onClickPlus(index) }
            )

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
fun BasketItem(
    item: BasketItemModel,
    onClickDelete: () -> Unit,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit
) {
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
                text = item.menuName,
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
                        modifier = Modifier
                            .padding(16.dp, 10.dp)
                            .clickable { onClickDelete() }
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
            // 메뉴 가격
            Text(
                text = formatPrice(item.menuPrice),
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
                        .background(color = LabelDisabled2)
                        .clickable { onClickMinus() },
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
                text = "${item.count}개",
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
                        .background(color = LabelNormal2)
                        .clickable { onClickPlus() },
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
        // Basket()
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
        //BasketList()
    }
}

