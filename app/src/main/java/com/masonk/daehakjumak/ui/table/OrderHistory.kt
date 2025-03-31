package com.masonk.daehakjumak.ui.table

import android.widget.Space
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun OrderHistory() {
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
                modifier = Modifier.size(40.dp),
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
                .height(630.dp)
        ) {
            OrderHistoryList()
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
fun OrderHistoryList() {
    LazyColumn() {
        items(12) {
            OrderHistoryItem()
        }
    }
}

@Composable
fun OrderHistoryItem() {
    Row(modifier = Modifier
        .padding(bottom = 24.dp)
        .fillMaxWidth()
        ) {
        Text(
            text = "메뉴이름",
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
            text = "10,000원",
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
        OrderHistory()
    }
}