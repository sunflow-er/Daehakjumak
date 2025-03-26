package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

@Composable
fun TableClearAlertDialog() {
    Dialog(onDismissRequest = {}) {
        Card(modifier = Modifier.size(698.dp, 244.dp)) {
            Column(
                modifier = Modifier
                    .padding(25.dp, 40.dp, 25.dp, 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "7번 테이블을 비우시겠습니까?",
                    fontSize = 30.sp
                )
                Row(modifier = Modifier.padding(top = 41.dp)) {
                    Button(
                        onClick = {},
                        modifier = Modifier.height(98.dp).weight(1f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "취소",
                            fontSize = 30.sp
                        )
                    }
                    Spacer(
                        modifier = Modifier.width(16.dp),
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier.height(98.dp).weight(1f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "비우기",
                            fontSize = 30.sp
                        )
                    }
                }
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    device = "spec:width=1551dp,height=1053dp,dpi=160"
)
@Composable
fun PreviewEmptyAlertDialog() {
    DaehakjumakTheme {
        TableClearAlertDialog()
    }
}