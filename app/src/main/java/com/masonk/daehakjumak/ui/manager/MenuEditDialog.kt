package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masonk.daehakjumak.core.enums.MenuAddEditDialogType
import com.masonk.daehakjumak.core.util.formatPriceIntToStr
import com.masonk.daehakjumak.core.util.formatPriceStrToInt
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.viewmodel.MenuManagementScreenViewModel
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNeutral2
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.LineStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun MenuEditDialog(
    menuManagementScreenViewModel: MenuManagementScreenViewModel,
    editedMenu: Pair<MenuModel, MenuAddEditDialogType>,
    onDismiss: () -> Unit
) {
    val menu = editedMenu.first // 수정할 메뉴
    val editType = editedMenu.second // 수정할 타입(이름/가격/사진)

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier.size(742.dp, 892.dp),
            colors = CardDefaults.cardColors(
                containerColor = LabelNeutral,
                contentColor = LabelStrong
            )
        ) {
            when (editType) {
                MenuAddEditDialogType.NAME -> { // 이름
                    MenuNameEditScreen(
                        menu = menu,
                        onClickMenuNameEditComplete = {newName: String -> menuManagementScreenViewModel.editMenuName(menu, newName) },
                        onDismiss = onDismiss
                    )
                }

                MenuAddEditDialogType.PRICE -> { // 가격
                    MenuPriceEditScreen(
                        menu = menu,
                        onClickMenuPriceEditComplete = {newPrice: Int -> menuManagementScreenViewModel.editMenuPrice(menu, newPrice)},
                        onDismiss = onDismiss,
                    )
                }

                MenuAddEditDialogType.IMAGE -> { // 이미지
                    MenuImageEditScreen(
                        menu = menu,
                        onClickMenuImageEditComplete = {newImage: String -> menuManagementScreenViewModel.editMenuImage(menu, newImage)},
                        onDismiss = onDismiss,
                    )
                }
            }
        }
    }

}

// 메뉴 이름 수정
@Composable
fun MenuNameEditScreen(
    menu: MenuModel,
    onClickMenuNameEditComplete: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf(menu.name) } // 메뉴 이름

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // X 아이콘
        Row(
            modifier = Modifier
                .padding(32.dp, 32.dp, 32.dp, 0.dp)
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
                    .clickable { onDismiss() },
                tint = LabelNormal
            )
        }

        // 설명
        Text(
            text = "메뉴 이름을 입력해주세요.",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        // 메뉴 이름
        Text(
            text = name,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(top = 34.dp),
            color = LabelStrong
        )

        // 가로선
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 63.dp)
                .fillMaxWidth(),
            thickness = 2.dp,
            color = LineStrong,
        )

        // TODO 천지인 키패드
        Box(modifier = Modifier.size(742.dp, 494.dp)) {

        }


        // 수정 완료 버튼
        Button(
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { // 이름 정보 수정, 다이얼로그 닫기
                    onClickMenuNameEditComplete(name)
                    onDismiss() // menuManagementScreenViewModel.finishEditMenu()
                },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryNormal,
                contentColor = LabelBlack
            ),
        ) {
            Text(
                text = "수정 완료",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

// 메뉴 이름 수정
@Composable
fun MenuPriceEditScreen(
    menu: MenuModel,
    onClickMenuPriceEditComplete: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val keys = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(-2, 0, -1) // -2: Reset, -1: delete
    )

    var price by remember { mutableStateOf(formatPriceIntToStr(menu.price)) } // 입력 가격 텍스트

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // X 아이콘
        Row(
            modifier = Modifier
                .padding(32.dp, 32.dp, 32.dp, 0.dp)
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
                    .clickable { onDismiss() },
                tint = LabelNormal
            )
        }

        // 설명
        Text(
            text = "금액을 입력해주세요.",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 4.dp)
        )

        // 금액
        Text(
            text = formatPriceIntToStr(formatPriceStrToInt(price)),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(top = 34.dp),
            color = LabelStrong
        )

        // 가로선
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 63.dp)
                .fillMaxWidth(),
            thickness = 2.dp,
            color = LineStrong,
        )

        // 숫자 키패드
        keys.forEachIndexed { rowIndex, row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEachIndexed { colIndex, key ->
                    Box(
                        modifier = Modifier
                            .height(123.dp)
                            .weight(1f)
                            .clickable { // 입력, 초기화, 지우기
                                when (key) {
                                    -2 -> { // 초기화
                                        price = ""
                                    }
                                    -1 -> { // 지우기
                                        if (price.isNotEmpty()) {
                                            price = price.dropLast(1)
                                        }
                                    }
                                    else -> { // 숫자 입력, 누적
                                        if (price.length < 7) {
                                            price += key.toString()
                                        }
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        when (key) {
                            -2 -> { // 초기화, 금액 정보 삭제
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "초기화 아이콘",
                                    modifier = Modifier
                                        .size(24.dp),
                                    tint = LabelNormal
                                )
                            }

                            -1 -> { // 지우기, 숫자 하나씩 제거
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "지우기 아이콘",
                                    modifier = Modifier
                                        .size(24.dp),
                                    tint = LabelNormal
                                )
                            }

                            else -> { // 숫자
                                Text(
                                    text = key.toString(),
                                    style = MaterialTheme.typography.displayLarge,
                                )
                            }
                        }

                    }

                    // 세로선
                    if (colIndex != row.lastIndex) {
                        VerticalDivider(
                            modifier = Modifier.fillMaxHeight(),
                            thickness = 2.dp,
                            color = LineStrong
                        )
                    }
                }

                // 가로선
                if (rowIndex != keys.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = LineStrong
                    )
                }
            }
        }

        // 다음 버튼
        Button(
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { // 가격 정보 수정, 다이얼로그 닫기
                    onClickMenuPriceEditComplete(formatPriceStrToInt(price)) // 가격 수정
                    onDismiss() // 다이얼로그 닫기
                },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryNormal,
                contentColor = LabelBlack
            ),
        ) {
            Text(
                text = "수정완료",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

// 메뉴 이미지 수정
@Composable
fun MenuImageEditScreen(
    menu: MenuModel,
    onClickMenuImageEditComplete: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize(),
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
                    .clickable { onDismiss() },
                tint = LabelNormal
            )
        }

        // 설명
        Text(
            text = "사진을 선택해주세요.",
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
                .padding(50.dp)
                .clickable {
                    val image = ""// TODO 갤러리
                    onClickMenuImageEditComplete(image)
                    onDismiss() // 다이얼로그 닫기
                },
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
                .padding(50.dp)
                .clickable {
                    val image = "" // TODO 사진 촬영
                    onClickMenuImageEditComplete(image)
                    onDismiss() // 다이얼로그 닫기
                },
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
                .padding(50.dp)
                .clickable {
                    onDismiss() // 다이얼로그 닫기
                },
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