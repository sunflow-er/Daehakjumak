package com.masonk.daehakjumak.ui.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.uistate.MenuBoardUiState
import com.masonk.daehakjumak.presentation.viewmodel.TableDialogViewModel
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong

// 메뉴판
@Composable
fun MenuBoard(
    tableDialogViewModel: TableDialogViewModel,
    onClickOrderHistory: () -> Unit,
) {
    val selectedMenuTypeIndex by tableDialogViewModel.selectedMenuTypeIndex.collectAsState() // 선택된 메뉴판 메뉴 타입
    val menuBoardUiState by tableDialogViewModel.menuBoardUiState.collectAsState() // 메뉴리스트

    val tabList = listOf( // 탭 목록
        "주메뉴",
        "음료",
    )

    // 메뉴판
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(32.dp, 32.dp, 32.dp, 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 가로탭 레이아웃
            TabRow(
                selectedTabIndex = selectedMenuTypeIndex, // 선택된 탭 인덱스
                containerColor = BackgroundNormal,
                contentColor = LabelStrong,
                modifier = Modifier
                    .width(320.dp),
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedMenuTypeIndex]),
                        color = LabelStrong
                    )
                }
            ) {
                tabList.forEachIndexed { index, type ->
                    // 탭
                    Tab(
                        selected = (selectedMenuTypeIndex == index),
                        onClick = { tableDialogViewModel.changeMenuTypeIndexTo(index) },
                        text = {
                            Text(
                                text = type,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 주문내역 버튼
            Surface(
                modifier = Modifier.size(118.dp, 40.dp),
                shape = MaterialTheme.shapes.medium,
                color = LabelNormal,
                contentColor = LabelBlack
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "주문내역",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(20.dp, 7.dp)
                            .clickable { onClickOrderHistory() } // 주문내역
                    )
                }
            }
        }


        // 주메뉴/음료 리스트 화면
        when (selectedMenuTypeIndex) {
            0 -> { // 주메뉴
                MenuGridList(
                    menuBoardUiState = menuBoardUiState,
                    onClickMenu = { menu: MenuModel -> tableDialogViewModel.addMenuToBasket(menu) }
                )
            }
            1 -> { // 음료
                MenuGridList(
                    menuBoardUiState = menuBoardUiState,
                    onClickMenu = { menu: MenuModel -> tableDialogViewModel.addMenuToBasket(menu) }
                )
            }
        }
    }
}

// 메뉴 리스트
@Composable
fun MenuGridList(
    menuBoardUiState: MenuBoardUiState,
    onClickMenu: (MenuModel) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (menuBoardUiState.isLoading) {
            CircularProgressIndicator()
        }
        if (menuBoardUiState.error != null) {
            // TODO 스낵바 메시지 띄우기
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(menuBoardUiState.menuList) { menu ->
                MenuItem(
                    menu = menu,
                    onClickMenu = onClickMenu
                )
            }
        }
    }

}

// 메뉴 아이템
@Composable
fun MenuItem(
    menu: MenuModel,
    onClickMenu: (MenuModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(360.dp)
            .clickable { onClickMenu(menu) }, // 메뉴 선택 시 장바구니에 추가
        border = BorderStroke(2.dp, LabelNormal),
        colors = CardDefaults.cardColors(containerColor = LabelNormal2, contentColor = LabelBlack)
    ) {
        // TODO SOLD-OUT 처리

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(color = LabelNeutral)
            ) {
                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "메뉴 사진",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = menu.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 30.dp)
            )
            Text(
                text = menu.price.toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
            )

        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1069dp,height=892dp,dpi=160",
)
@Composable
fun PreviewMenuBoard() {
    DaehakjumakTheme {
        //MenuBoard()
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=324dp,height=360dp,dpi=160",
)
@Composable
fun previewMenuItem() {
    DaehakjumakTheme {
        // MenuItem()
    }
}