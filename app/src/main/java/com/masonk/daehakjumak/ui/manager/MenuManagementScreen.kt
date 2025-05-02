package com.masonk.daehakjumak.ui.manager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masonk.daehakjumak.core.util.formatPrice
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.uistate.MenuBoardUiState
import com.masonk.daehakjumak.presentation.viewmodel.ManagerScreenViewModel
import com.masonk.daehakjumak.presentation.viewmodel.MenuManagementScreenViewModel
import com.masonk.daehakjumak.ui.theme.BackgroundNormal
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelBlack
import com.masonk.daehakjumak.ui.theme.LabelDisabled
import com.masonk.daehakjumak.ui.theme.LabelDisabled2
import com.masonk.daehakjumak.ui.theme.LabelNeutral
import com.masonk.daehakjumak.ui.theme.LabelNeutral2
import com.masonk.daehakjumak.ui.theme.LabelNormal
import com.masonk.daehakjumak.ui.theme.LabelNormal2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.theme.PrimaryNormal

@Composable
fun MenuManagementScreen(
    managerScreenViewModel: ManagerScreenViewModel,
    menuManagementScreenViewModel: MenuManagementScreenViewModel
) {
    val selectedMenuTypeIndex by menuManagementScreenViewModel.selectedMenuTypeIndex.collectAsState() // 선택된 메뉴판 메뉴 타입
    val menuBoardUiState by menuManagementScreenViewModel.menuBoardUiState.collectAsState() // 메뉴리스트

    val tabList = listOf(
        // 탭 목록
        "주메뉴",
        "음료",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundNormal)
            .padding(36.dp)
    ) {
        // head
        Row() {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "뒤로가기 아이콘",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { managerScreenViewModel.onClickBackToMainManagement() } // MainManagementScreenViewModel로 이동
            )
            Text(
                text = "메뉴 관리",
                style = MaterialTheme.typography.titleLarge,
                color = LabelStrong,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // 가로탭 레이아웃
        TabRow(
            selectedTabIndex = selectedMenuTypeIndex, // 선택된 탭 인덱스
            containerColor = BackgroundNormal,
            contentColor = LabelStrong,
            modifier = Modifier
                .padding(top = 47.dp)
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
                    onClick = { menuManagementScreenViewModel.changeMenuTypeIndexTo(index) },
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
    }

    // 주메뉴/음료 리스트 화면
    when (selectedMenuTypeIndex) {
        0 -> { // 주메뉴
            MenuEditGridList(
                menuBoardUiState = menuBoardUiState,
                menuManagementScreenViewModel = menuManagementScreenViewModel
            )
        }

        1 -> { // 음료
            MenuEditGridList(
                menuBoardUiState = menuBoardUiState,
                menuManagementScreenViewModel = menuManagementScreenViewModel
            )
        }
    }
}


// 메뉴 리스트
@Composable
fun MenuEditGridList(
    menuBoardUiState: MenuBoardUiState,
    menuManagementScreenViewModel: MenuManagementScreenViewModel,
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
                MenuEditItem(
                    menu = menu,
                    onClickDeleteMenu = { menu: MenuModel ->
                        menuManagementScreenViewModel.deleteMenu(
                            menu
                        )
                    }, // 메뉴 삭제
                    onClickEditMenuImage = { menuManagementScreenViewModel.editMenuImage() }, // 메뉴 이미지 수정
                    onClickEditMenuName = { menuManagementScreenViewModel.editMenuName() }, // 메뉴 이름 수정
                    onClickEditMenuPrice = { menuManagementScreenViewModel.editMenuPrice() }, // 메뉴 가격 수정
                    onClickSoldOutMenu = { menu: MenuModel ->
                        menuManagementScreenViewModel.soldOutMenu(
                            menu
                        )
                    } // 메뉴 품절 등록
                )
            }

            // 추가 버튼
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(360.dp)
                        .padding(bottom = 16.dp)
                        .background(color = LabelNeutral2, shape = MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "플러스 아이콘",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }

}

// 메뉴 아이템
@Composable
fun MenuEditItem(
    menu: MenuModel,
    onClickDeleteMenu: (MenuModel) -> Unit,
    onClickEditMenuImage: (MenuModel) -> Unit,
    onClickEditMenuName: (MenuModel) -> Unit,
    onClickEditMenuPrice: (MenuModel) -> Unit,
    onClickSoldOutMenu: (MenuModel) -> Unit
) {
    // 다이얼로그 표시 여부를 제어할 상태
    var showMenuDeleteAlertDialog by remember { mutableStateOf(false) } // 메뉴 삭제 시 true로
    var showMenuImageEditDialog by remember { mutableStateOf(false) } // 메뉴 이미지 수정 다이얼로그
    var showMenuNameEditDialog by remember { mutableStateOf(false) } // 메뉴 이름 수정 다이얼로그
    var showMenuPriceEditDialog by remember { mutableStateOf(false) } // 메뉴 가격 수정 다이얼로그

    // 다이얼로그 표시 조건
    when {
        showMenuDeleteAlertDialog -> { // 메뉴 삭제 다이얼로그
            MenuDeleteAlertDialog(
                menu = menu,
                onClickDelete = { menu: MenuModel ->
                    onClickDeleteMenu(menu)
                },
                onDismiss = { showMenuDeleteAlertDialog = false }
            )
        }

        showMenuImageEditDialog -> { // 메뉴 이미지 수정 다이얼로그
            MenuImageEditDialog()
        }

        showMenuNameEditDialog -> { // 메뉴 이름 수정 다이얼로그
            MenuNameEditDialog()
        }

        showMenuPriceEditDialog -> { // 메뉴 가격 수정 다이얼로그
            MenuPriceEditDialog()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(bottom = 16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, LabelNormal),
            colors = CardDefaults.cardColors(
                containerColor = LabelNormal2,
                contentColor = LabelBlack
            )
        ) {
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
                    // 메뉴 사진
                    Image(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "메뉴 사진",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    // 메뉴 사진 수정 버튼
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "메뉴 사진 수정 아이콘",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { // 메뉴 사진 수정 다이얼로그 띄우기
                                showMenuImageEditDialog = true
                            }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 메뉴 이름
                    Text(
                        text = menu.name,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    // 메뉴 이름 수정 아이콘
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "메뉴 이름 수정 아이콘",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(40.dp)
                            .clickable { // 메뉴 이름 수정 다이얼로그 띄우기
                                showMenuNameEditDialog = true
                            }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 메뉴 가격
                    Text(
                        text = formatPrice(menu.price),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
                    )
                    // 메뉴 가격 수정 아이콘
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "메뉴 가격 수정 아이콘",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(40.dp)
                            .clickable { // 메뉴 가격 수정 다이얼로그 띄우기
                                showMenuPriceEditDialog = true
                            }
                    )
                    // 품절등록 버튼
                    Surface(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(74.dp, 32.dp),
                        shape = MaterialTheme.shapes.extraSmall,
                        color = LabelDisabled,
                        contentColor = LabelDisabled2,
                        border = BorderStroke(width = 1.dp, LabelDisabled2)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "품절 등록",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                                    .padding(7.dp)
                                    .clickable { // 품절 등록
                                        onClickSoldOutMenu(menu)
                                    }
                            )
                        }
                    }
                }
            }
        }

        // 삭제 뱃지
        Badge(
            modifier = Modifier
                .size(30.dp)
                .offset(x = 12.dp, y = (-12).dp)
                .clickable { // 메뉴 삭제 다이얼로그 띄우기
                    showMenuDeleteAlertDialog = true
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
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1069dp,height=892dp,dpi=160",
)
@Composable
fun PreviewMenuManagementScreen() {
    DaehakjumakTheme {
        // MenuManagementScreen()
    }
}