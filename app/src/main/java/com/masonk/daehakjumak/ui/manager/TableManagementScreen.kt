package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.viewmodel.ManagerScreenViewModel
import com.masonk.daehakjumak.presentation.viewmodel.TableManagementScreenViewModel
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal
import java.util.UUID
import kotlin.math.roundToInt

@Composable
fun TableManagementScreen(
    managerScreenViewModel: ManagerScreenViewModel,
    tableManagementScreenViewModel: TableManagementScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(36.dp)
    ) {
        // Head
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "뒤로가기 아이콘",
                modifier = Modifier.size(40.dp).clickable {
                    // 메인 관리 화면으로 돌아가기
                    managerScreenViewModel.onClickBackToMainManagement()
                }
            )
            Text(
                text = "테이블 관리",
                style = MaterialTheme.typography.titleLarge,
                color = LabelStrong,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(top = 29.dp),
            thickness = 2.dp,
            color = Color.Gray
        )


        // 테이블 배치 레이아웃
        TableLayout(tableManagementScreenViewModel)
    }
}

@Composable
fun TableLayout(tableManagementScreenViewModel: TableManagementScreenViewModel) {
    val tableSize = 304.dp // 테이블 크기, width = 304dp, height = 304dp
    val tableSizePx = with(LocalDensity.current) { tableSize.toPx() } // dp에서 px로 변환

    // 테이블 레이아웃 크기 정보
    var layoutWidth by remember { mutableStateOf(0f) }
    var layoutHeight by remember { mutableStateOf(0f) }

    // 테이블 리스트
    val tableListUiState by tableManagementScreenViewModel.tableListUiState.collectAsState()
    val tableList = tableListUiState.tableList // 이미 존재하는 테이블 리스트

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                // 테이블 레이아웃 크기 저장
                layoutWidth = it.size.width.toFloat()
                layoutHeight = it.size.height.toFloat()
            }
            .pointerInput(Unit) {
                // 터치 이벤트 리스너 등록
                detectTapGestures { tapCoord -> // 터치 지점
                    // 터치 지점을 기준으로 한 새 테이블 위치, 중심 좌표 기준 상하좌우 좌표 계산
                    val newStart = tapCoord.x - tableSizePx / 2 // start
                    val newTop = tapCoord.y - tableSizePx / 2 // top
                    val newEnd = newStart + tableSizePx // end
                    val newBottom = newTop + tableSizePx // bottom

                    // 기존 테이블과 겹치는지
                    val isOverlapping = tableList.any { table ->
                        val tableStart = table.coordinate.x - tableSizePx / 2 // start
                        val tableTop = table.coordinate.y - tableSizePx / 2 // top
                        val tableEnd = tableStart + tableSizePx // end
                        val tableBottom = tableTop + tableSizePx // bottom

                        // AABB
                        !(newEnd <= tableStart || newStart >= tableEnd || newBottom <= tableTop || newTop >= tableBottom)
                    }

                    // 테이블 배치 레이아웃 내부에 있는지
                    val isInsideLayout =
                        newStart >= 0 && newTop >= 0 && newEnd <= layoutWidth && newBottom <= layoutHeight

                    // 최종 검사
                    if (!isOverlapping && isInsideLayout) { // 기존 테이블과 충돌하지 않고, 레이아웃 내부에 존재한다면
                        // 기존 테이블 리스트에 새로운 테이블 추가
                        tableManagementScreenViewModel.addTable(
                            TableModel(
                                id = UUID.randomUUID().toString(),
                                number = (tableList.size + 1).toString(),
                                coordinate = Coordinate(tapCoord.x, tapCoord.y),
                                orderList = emptyList() // OrderModel
                            )
                        )

                    }

                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (tableListUiState.isLoading) { // 테이블 리스트 로딩 중
            CircularProgressIndicator()
        }
        if (tableListUiState.error != null) { // 에러
            // TODO 스낵바 메시지
        }
        if (tableListUiState.tableList.isEmpty()) { // 테이블 리스트가 비어있으면, 아무 테이블도 없으면
            Text(
                text = "빈 곳을 터치하여 테이블을 배치해보세요!",
                style = MaterialTheme.typography.displaySmall,
                color = LabelStrong,
                modifier = Modifier.padding(top = 56.dp),
            )
        } else { // 테이블 리스트가 있으면
            // 기존의 테이블 리스트를 그림
            tableList.forEach { table ->
                // 테이블 아이템
                TableEditItem(
                    table = table, // 테이블 정보
                    modifier = Modifier
                        .offset { // 테이블 위치 지정 (테이블 좌측 상단 기준)
                            IntOffset(
                                (table.coordinate.x - tableSizePx / 2).roundToInt(),
                                (table.coordinate.y - tableSizePx / 2).roundToInt()
                            )
                        }
                        .size(tableSize)
                        .pointerInput(table.id) { // 개별(id) 테이블 드래그 인식
                            var startCoord = table.coordinate // 드래그 시작 좌표

                            // 마지막 좌표
                            var latestX = table.coordinate.x
                            var latestY = table.coordinate.y

                            detectDragGestures(
                                // 드래그 시작
                                onDragStart = {
                                    startCoord = table.coordinate // 드래그 시작 시 기존 좌표 저장
                                },
                                // 드래그 중
                                onDrag = { change, dragAmount ->
                                    // 드래그 중 테이블의 새로운 중심 위치 (드래그 후)
                                    val newX = latestX + dragAmount.x
                                    val newY = latestY + dragAmount.y

                                    // 드래그 중 테이블의 새로운 위치/영역
                                    val newStart = newX - tableSizePx / 2
                                    val newTop = newY - tableSizePx / 2
                                    val newEnd = newStart + tableSizePx
                                    val newBottom = newTop + tableSizePx

                                    // 다른 테이블과 겹치는지
                                    val isOverlapping = tableList.any { otherTable ->
                                        if (otherTable.id == table.id) return@any false // 자기 자신 제외

                                        val otherStart = otherTable.coordinate.x - tableSizePx / 2
                                        val otherTop = otherTable.coordinate.y - tableSizePx / 2
                                        val otherEnd = otherStart + tableSizePx
                                        val otherBottom = otherTop + tableSizePx

                                        !(newEnd <= otherStart || newStart >= otherEnd || newBottom <= otherTop || newTop >= otherBottom)
                                    }

                                    // 레이아웃을 영역을 넘어가는지
                                    val isInsideLayout =
                                        newStart >= 0 && newTop >= 0 && newEnd <= layoutWidth && newBottom <= layoutHeight

                                    // 최종점검
                                    if (isInsideLayout && !isOverlapping) {
                                        // 마지막 좌표 업데이트
                                        latestX = newX
                                        latestY = newY

                                        // UI에 즉시 임시 좌표 반영
                                        tableManagementScreenViewModel.tempUpdateTableCoord(
                                            table.id,
                                            Coordinate(latestX, latestY)
                                        )
                                    }

                                    change.consume() // 이벤트 소비
                                },
                                // 드래그 끝
                                onDragEnd = {
                                    // 테이블 최종 좌표 서버 저장
                                    tableManagementScreenViewModel.updateTableCoord(
                                        tableId = table.id,
                                        newCoord = Coordinate(latestX, latestY),
                                        oldCoord = startCoord
                                    )
                                }
                            )
                        },
                    onClickDeleteTable = { tableManagementScreenViewModel.removeTable(table.id) }
                )
            }
        }
    }
}

@Composable
fun TableEditItem(table : TableModel, modifier: Modifier, onClickDeleteTable: () -> Unit) {
    Box(modifier = modifier) {
        // 테이블
        Card(
            modifier = Modifier
                .size(288.dp)
                .align(Alignment.BottomStart) // 좌측 하단 위치
                .padding(18.dp, 12.dp),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, LabelNormal),
            colors = CardDefaults.cardColors(
                containerColor = LabelNeutral,
                contentColor = LabelNormal
            )
        ) {
            // 테이블 번호
            Text(
                text = table.number,
                style = MaterialTheme.typography.bodyLarge,
                color = LabelNormal
            )

            // Divider
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth(),
                thickness = 2.dp,
                color = LabelNeutral
            )
        }

        // 삭제 아이콘/뱃지
        Badge(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.TopEnd) // 우측 상단 위치
                .clickable {
                    onClickDeleteTable()
                },
            containerColor = PrimaryNormal
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "삭제 아이콘",
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
fun PreviewTableSettingScreen() {
    DaehakjumakTheme {
        // TableManagementScreen()
    }
}