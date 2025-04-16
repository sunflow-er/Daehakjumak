package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.masonk.daehakjumak.core.enums.OrderScreenType
import com.masonk.daehakjumak.core.enums.TableDialogStep
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.viewmodel.TableDialogViewModel
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelNormal

// 테이블 다이얼로그 화면 (주문, QR결제, 완료)
@Composable
fun TableDialog(
    tableDialogViewModel: TableDialogViewModel,
    selectedTable: TableModel, // 선택한 테이블
    onDismiss: () -> Unit
) {
    // 테이블 다이얼로그 단계
    val tableDialogStep by tableDialogViewModel.tableDialogStep.collectAsState()

    Dialog(
        onDismissRequest = { // 다이얼로그 닫기, 외부 클릭/백버튼 시
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = false, // 백버튼 허용
            dismissOnClickOutside = true, // 외부 클릭 무시
            usePlatformDefaultWidth = false // 기본 너비 제한 해제
        )
    ) {
        Card(
            modifier = Modifier
                .padding(80.dp)
                .fillMaxSize(),
            shape = MaterialTheme.shapes.large // 20dp
        ) {
            // 단계 상태에 따라 화면 띄우기
            when (tableDialogStep) {
                TableDialogStep.ORDER -> { // 주문 단계
                    // 주문 화면
                    TableOrderScreen(
                        tableDialogViewModel = tableDialogViewModel,
                        selectedTable = selectedTable, // 선택한 테이블
                        onClickPay = { tableDialogViewModel.moveDialogStepTo(TableDialogStep.QR) },
                        onDismiss = {
                            onDismiss()
                        }
                    ) // 결제 버튼 클릭 시, QR 단계로 전환
                }

                TableDialogStep.QR -> { // QR 결제 단계
                    // QR 화면
                    TableQRScreen(
                        onDismiss = {
                            onDismiss()
                        },
                        onClickQR = {
                            tableDialogViewModel.moveDialogStepTo(
                                TableDialogStep.COMPLETE
                            )
                        },
                    ) // 결제 확인 시, 완료 단계로 전환
                }

                TableDialogStep.COMPLETE -> { // 완료 단계
                    // 완료
                    TableCompleteScreen(
                        onDismiss = {
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

// 테이블 주문 화면
@Composable
fun TableOrderScreen(
    tableDialogViewModel: TableDialogViewModel,
    selectedTable: TableModel,
    onDismiss: () -> Unit,
    onClickPay: () -> Unit,
) {
    val orderScreenType by tableDialogViewModel.orderScreenType.collectAsState() // 주문 화면 타입

    Row(modifier = Modifier.fillMaxSize()) {
        // 메뉴판/주문내역
        Box(modifier = Modifier.weight(2f)) {
            when (orderScreenType) { // 주문 화면 타입에 따라
                OrderScreenType.MENU_BOARD -> { // 메뉴판 화면
                    MenuBoard(
                        tableDialogViewModel,
                        onClickOrderHistory = { // '주문내역' 클릭 시
                            tableDialogViewModel.changeOrderScreenTypeTo( // 주문내역 화면으로 전환
                                OrderScreenType.ORDER_HISTORY
                            )
                        }
                    )
                }

                OrderScreenType.ORDER_HISTORY -> { // 주문 내역 화면
                    OrderHistory(
                        tableDialogViewModel,
                        onClickBack = { // 뒤로가기 아이콘 클릭 시
                            tableDialogViewModel.changeOrderScreenTypeTo( // 메뉴판 화면으로 전환
                                OrderScreenType.MENU_BOARD
                            )
                        })
                }
            }
        }

        // Divider
        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            thickness = 2.dp,
            color = LabelNormal
        )

        // 장바구니
        Box(modifier = Modifier.weight(1f)) {
            Basket(
                tableDialogViewModel = tableDialogViewModel,
                selectedTable = selectedTable,
                onDismiss = {
                    onDismiss()
                },
                onClickPay = onClickPay,
            )
        }
    }
}

// 테이블 결제 QR코드 화면
@Composable
fun TableQRScreen(
    onDismiss: () -> Unit,
    onClickQR: () -> Unit
) {
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
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onDismiss() } // 다이얼로그 닫기
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
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = { // 더블탭하면
                                        onClickQR() // 완료화면으로 이동
                                    }
                                )
                            }
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
fun TableCompleteScreen(
    onDismiss: () -> Unit,
) {
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
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onDismiss() } // 다이얼로그 닫기
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

        Text(
            text = "결제가 완료되었습니다.",
            fontSize = 40.sp,
            modifier = Modifier.padding(43.dp)
        )

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

    }
}