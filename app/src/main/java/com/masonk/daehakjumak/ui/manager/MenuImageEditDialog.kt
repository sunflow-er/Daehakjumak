package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNeutral2
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelStrong

@Composable
fun MenuImageEditDialog() {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier.size(742.dp, 892.dp),
            colors = CardDefaults.cardColors(
                containerColor = LabelNeutral,
                contentColor = LabelStrong
            )
        ) {
            Column(
                modifier = Modifier.padding(25.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // X 아이콘
                Row(
                    modifier = Modifier
                        .padding(7.dp, 7.dp, 7.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    // 빈공간
                    Spacer(modifier = Modifier.weight(1f))

                    // 다이얼로그 닫기
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "다이얼로그 닫기 아이콘",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                        tint = LabelNormal
                    )
                }

                // 설명
                Text(
                    text = "사진 등록 방법을 선택해주세요.",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // padding
                Spacer(modifier = Modifier.height(74.dp))

                Row(
                    modifier = Modifier
                        .height(205.dp)
                        .fillMaxWidth()
                        .background(color = LabelNeutral2)
                        .padding(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "'갤러리에서 가져오기' 아이콘",
                        modifier = Modifier
                            .size(130.dp),
                    )
                    Spacer(
                        modifier = Modifier.width(64.dp)
                    )
                    Text(
                        text = "갤러리에서 가져오기",
                        style = MaterialTheme.typography.headlineLarge,
                        color = LabelStrong,
                    )
                }

                // padding
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .height(205.dp)
                        .fillMaxWidth()
                        .background(color = LabelNeutral2)
                        .padding(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "'사진 촬영하기' 아이콘",
                        modifier = Modifier
                            .size(130.dp),
                    )
                    Spacer(
                        modifier = Modifier.width(64.dp)
                    )
                    Text(
                        text = "사진 촬영하기",
                        style = MaterialTheme.typography.headlineLarge,
                        color = LabelStrong,
                    )
                }

                // padding
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .height(205.dp)
                        .fillMaxWidth()
                        .background(color = LabelNeutral2)
                        .padding(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "'사진 등록하지 않기' 아이콘",
                        modifier = Modifier
                            .size(130.dp),
                    )
                    Spacer(
                        modifier = Modifier.width(64.dp)
                    )
                    Text(
                        text = "사진 등록하지 않기",
                        style = MaterialTheme.typography.headlineLarge,
                        color = LabelStrong,
                    )
                }
            }
        }
    }
}
