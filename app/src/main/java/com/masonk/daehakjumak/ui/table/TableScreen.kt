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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.core.enums.OrderStatus
import com.masonk.daehakjumak.presentation.model.OrderModel
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.model.uistate.TableListUiState
import com.masonk.daehakjumak.presentation.viewmodel.TableScreenViewModel
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.StatusPreparing
import com.masonk.daehakjumak.ui.theme.StatusReady
import com.masonk.daehakjumak.ui.theme.StatusServed

// 테이블 화면
@Composable
fun TableScreen(tableScreenViewModel: TableScreenViewModel) {
    val orderedTable by tableScreenViewModel.orderedTable.collectAsState() // 선택한 테이블 아이디
    val clearedTable by tableScreenViewModel.clearedTable.collectAsState()  // 비우기한 테이블

    // 주막 이름, 테이블 리스트
    val jumakNameUiState by tableScreenViewModel.jumakNameUiState.collectAsState()
    val tableListUiState by tableScreenViewModel.tableListUiState.collectAsState()

    // 선택된 테이블이 있으면, TableDialog 띄우기
    if (orderedTable != null) {
        TableDialog(
            tableDialogViewModel = , // TODO DI
            selectedTable = orderedTable!!,
            onDismiss = { tableScreenViewModel.finishTableOrder() }
        )
    }
    
    // 비우기 누른 테이블이 있으면, TableClearAlertDialog 띄우기
    if (clearedTable != null) {
        TableClearAlertDialog(
            clearedTable = clearedTable!!,
            onClickClear = { tableScreenViewModel.clearTableOrderList() },
            onDismiss = { tableScreenViewModel.finishClearTable() }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(36.dp)
    ) {

        // 주막 이름
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (jumakNameUiState.isLoading) {
                CircularProgressIndicator()
            }
            if (jumakNameUiState.error != null) {
                // TODO 에러 메시지 띄우기
            }
            Text(
                text = jumakNameUiState.jumakName, // 주막 이름
                style = MaterialTheme.typography.displayLarge,
                color = LabelStrong,
            )
        }

        // 테이블 리스트
        TableList(
            tableListUiState = tableListUiState,
            onClickTable = { table: TableModel -> tableScreenViewModel.startTableOrder(table) }, // 테이블 클릭/선택
            onClickClear = { table: TableModel -> tableScreenViewModel.startClearTable(table) } // 테이블 비우기
        )
    }
}

// 테이블 리스트
@Composable
fun TableList(
    tableListUiState: TableListUiState,
    onClickTable: (TableModel) -> Unit, // 테이블 클릭
    onClickClear: (TableModel) -> Unit, // 테이블 비우기
) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (tableListUiState.isLoading) {
            CircularProgressIndicator()
        }
        if (tableListUiState.error != null) {
            // TODO 스낵바 메시지 띄우기
        }
        tableListUiState.tableList.forEach { table ->
            TableItem(
                table = table,
                onClickTable = onClickTable, // 테이블 선택/클릭
                onClickClear = onClickClear
            )
        }
    }
}

// 테이블 카드
@Composable
fun TableItem(
    table: TableModel,
    onClickTable: (TableModel) -> Unit,
    onClickClear: (TableModel) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LabelNeutral,
            contentColor = StatusServed
        ),
        border = BorderStroke(2.dp, LabelNormal2),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .size(288.dp)
            .offset((table.coordinate.x).dp, (table.coordinate.y).dp) // 테이블 위치/좌표
            .clickable { onClickTable(table) }, // 테이블 선택
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
                    text = table.number, // 테이블 번호
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text( // 비우기 버튼
                    text = "비우기",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.clickable {
                        onClickClear(table) // 테이블 주문 내역 비우기
                    }
                )
            }

            Spacer(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth()
                    .height(2.dp)
            )

            // 테이블 주문 리스트
            TableOrderList(table.orderList)
        }
    }
}

// 테이블 주문 리스트
@Composable
fun TableOrderList(orderList: List<OrderModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(orderList) { order ->
            TableOrderItem(order)
        }
    }
}

// 테이블 주문 칩
@Composable
fun TableOrderItem(order: OrderModel) {
    val (itemIcon, itemColor) = when (order.status) {
        OrderStatus.PREPARING -> Icons.Default.CheckCircle to StatusPreparing
        OrderStatus.COOKING -> Icons.Default.CheckCircle to StatusReady
        OrderStatus.SERVED -> Icons.Default.CheckCircle to StatusServed
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        color = itemColor, // 주문 상태에 따른 아이템 컬러
        contentColor = LabelBlack
    ) {
        Row(
            modifier = Modifier.padding(8.dp, 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 메뉴 아이콘
            Icon(
                imageVector = itemIcon, // 주문 상태에 따른 아이콘
                contentDescription = "아이콘",
                // modifier = Modifier.size(20.dp, 20.dp)
            )

            // 메뉴 이름
            Text(
                text = order.menuName,
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
        // TableItem()
    }
}