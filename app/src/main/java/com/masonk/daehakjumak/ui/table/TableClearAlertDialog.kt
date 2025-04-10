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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.viewmodel.TableScreenViewModel
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal
import com.masonk.daehakjumak.ui.theme.StatusServed

@Composable
fun TableClearAlertDialog(
    clearedTable: TableModel,
    onClickClear: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.size(698.dp, 244.dp),
            colors = CardDefaults.cardColors(
                containerColor = LabelNeutral,
                contentColor = LabelStrong
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp, 40.dp, 25.dp, 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${clearedTable.number}번 테이블을 비우시겠습니까?",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Row(modifier = Modifier.padding(top = 41.dp)) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .height(98.dp)
                            .weight(1f),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LabelDisabled,
                            contentColor = LabelBlack
                        )
                    ) {
                        Text(
                            text = "취소",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Spacer(
                        modifier = Modifier.width(16.dp),
                    )
                    Button(
                        onClick = {
                            onClickClear() // 서버에 테이블 비움 요청
                            onDismiss() // 다이얼로그 닫기
                        },
                        modifier = Modifier
                            .height(98.dp)
                            .weight(1f),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryNormal,
                            contentColor = LabelBlack
                        )
                    ) {
                        Text(
                            text = "비우기",
                            style = MaterialTheme.typography.headlineSmall
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
        // TableClearAlertDialog()
    }
}