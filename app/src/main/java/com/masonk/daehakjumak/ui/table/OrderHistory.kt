package com.masonk.daehakjumak.ui.table

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.presentation.model.OrderModel
import com.masonk.daehakjumak.presentation.model.uistate.OrderHistoryUiState
import com.masonk.daehakjumak.presentation.viewmodel.TableDialogViewModel
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun OrderHistory(
    tableDialogViewModel: TableDialogViewModel,
    onClickBack: () -> Unit
) {
    val orderHistoryUiState by tableDialogViewModel.orderHistoryUiState.collectAsState() // 주문내역 리스트

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(20.dp)
    ) {
        // Head
        Row {
            // 뒤로가기 아이콘
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "뒤로가기 아이콘",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onClickBack() }, // 뒤로가기, 메뉴판 화면으로
                tint = Color.White
            )

            // 주문내역 텍스트
            Text(
                text = "주문내역",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(28.dp, 48.dp, 28.dp, 0.dp)
                .fillMaxWidth()
                .height(630.dp),
            contentAlignment = Alignment.Center
        ) {
            OrderHistoryList(orderHistoryUiState)
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(28.dp, 32.dp, 28.dp, 0.dp),
            thickness = 2.dp,
            color = LabelStrong
        )

        // Tail
        Row(
            modifier = Modifier
                .padding(28.dp, 32.dp, 28.dp, 0.dp)
        ) {
            Text(
                text = "합계",
                style = MaterialTheme.typography.displayMedium,
                color = LabelStrong
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "30,000원",
                style = MaterialTheme.typography.displayMedium,
                color = PrimaryNormal
            )
        }


    }
}

@Composable
fun OrderHistoryList(orderHistoryUiState: OrderHistoryUiState) {
    if (orderHistoryUiState.isLoading) {
        CircularProgressIndicator()
    }
    if (orderHistoryUiState.error != null) {
        // TODO 스낵바 메시지 띄우기
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(orderHistoryUiState.orderHistory) { order ->
            OrderHistoryItem(order)
        }
    }
}

@Composable
fun OrderHistoryItem(order: OrderModel) {
    Row(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = order.menuName,
            style = MaterialTheme.typography.titleMedium,
            color = LabelStrong
        )
        Spacer(modifier = Modifier.weight(134f))
        Text(
            text = "1개",
            style = MaterialTheme.typography.titleMedium,
            color = LabelStrong
        )
        Spacer(modifier = Modifier.weight(589f))
        Text(
            text = order.menuPrice,
            style = MaterialTheme.typography.titleLarge,
            color = LabelStrong
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1069dp,height=892dp,dpi=160",
)
@Composable
fun PreviewOrderHistory() {
    DaehakjumakTheme {
        // OrderHistory()
    }
}