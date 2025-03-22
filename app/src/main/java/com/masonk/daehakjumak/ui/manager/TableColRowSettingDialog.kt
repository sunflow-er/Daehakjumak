package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import androidx.compose.foundation.layout.Box as Box

@Composable
fun TableColRowSettingDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(modifier = Modifier.padding(80.dp).fillMaxSize()) {
            Column(
                modifier = Modifier.padding(36.dp, 21.dp, 36.dp, 36.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "완료",
                        fontSize = 28.sp,
                    )
                }
                Text(
                    text = "드래그하여 열과 행을 선택해주세요.",
                    fontSize = 34.sp,
                    modifier = Modifier.padding(top = 18.dp)
                )

                DragSelectGrid()

                // 선택된 크기 출력
                Text(
                    text = "현재 1열 2행",
                    color = Color.Green,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(top = 18.dp)
                )
            }

        }
    }
}

@Composable
fun DragSelectGrid(
    maxRows: Int = 5,
    maxCols: Int = 6,
    cellWidth: Dp = 108.dp,
    cellHeight: Dp = 84.dp
) {
    var selectedRow by remember { mutableStateOf(0) }
    var selectedCol by remember { mutableStateOf(0) }

    val cellWidthPx = with(LocalDensity.current) { cellWidth.toPx() }
    val cellHeightPx = with(LocalDensity.current) { cellHeight.toPx() }

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        selectedCol = ((offset.x / cellWidthPx).toInt() + 1).coerceAtMost(maxCols)
                        selectedRow = ((offset.y / cellHeightPx).toInt() + 1).coerceAtMost(maxRows)
                    },
                    onDrag = { change, _ ->
                        val x = change.position.x
                        val y = change.position.y
                        selectedCol = ((x / cellWidthPx).toInt() + 1).coerceAtMost(maxCols)
                        selectedRow = ((y / cellHeightPx).toInt() + 1).coerceAtMost(maxRows)
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 열 번호
        Row(modifier = Modifier.padding(top = 18.dp)) {
            Spacer(
                modifier = Modifier.width(cellWidth)
            ) // 왼쪽 상단 빈칸
            for (col in 1..maxCols) {
                Text(
                    text = "$col",
                    color = if (col <= selectedCol) Color.Green else Color.Gray,
                    modifier = Modifier
                        .width(cellWidth)
                        .wrapContentSize(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.width(cellWidth))
        }

        // 격자 영역
        for (row in 1..maxRows) {
            Row {
                // 행 번호
                Text(
                    text = "$row",
                    color = if (row <= selectedRow) Color.Green else Color.Gray,
                    modifier = Modifier
                        .size(cellWidth, cellHeight)
                        .wrapContentSize(Alignment.CenterEnd)
                )
                for (col in 1..maxCols) {
                    val isSelected = row <= selectedRow && col <= selectedCol
                    Box(
                        modifier = Modifier
                            .size(cellWidth, cellHeight)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) Color.Green else Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.size(cellWidth, cellHeight))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


    }
}


@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=1556dp,height=892dp,dpi=160",
)
@Composable
fun PreviewColRowSettingDialog() {
    DaehakjumakTheme {
        TableColRowSettingDialog()

    }
}