package com.masonk.daehakjumak.ui.waiting

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

data class WaitingItem( // 나중에 옮기기
    val tableIndex: String, // 예: "01"
    val tableNumber: String, // 예: "1번"
    val peopleCount: Int,    // 예: 2
    val phoneNumber: String  // 예: "010-0000-0000"
)

enum class DialogStep { PEOPLE_SELECT, PHONE_INPUT, COMPLETE, NONE }

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=1551dp,height=1053dp,dpi=160",
)
@Composable
fun WaitingScreen() {
    var currentDialog by remember { mutableStateOf(DialogStep.NONE) }

    val waitList = remember {
        listOf(
            WaitingItem("01", "1번", 2, "010-0000-0000"),
            WaitingItem("02", "3번", 4, "010-8888-8888"),
            WaitingItem("03", "5번", 2, "010-7777-7777"),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // 전체 배경 (스크린샷 상 어두운 느낌)
            .padding(16.dp)
    ) {
        // 상단 제목
        Text(
            text = "대기",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // '대기 등록' 큰 버튼 (녹색 배경)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF60D160)) // 연한 녹색 예시
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // 가운데 큰 '+' 표시 (등록)
            TextButton(onClick = { currentDialog = DialogStep.PEOPLE_SELECT }) {
                Text(
                    text = "+",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 대기 명단 리스트
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(waitList) { item ->
                WaitingItemRow(item)
            }
        }
    }

    // 모달 표시
    when (currentDialog) {
        DialogStep.PEOPLE_SELECT -> {
            PeopleSelectDialog(
                onDismiss = { currentDialog = DialogStep.NONE },
                onNext = { currentDialog = DialogStep.PHONE_INPUT }
            )
        }
        DialogStep.PHONE_INPUT -> {
            PhoneInputDialog(
                onDismiss = { currentDialog = DialogStep.NONE },
                onNext = { currentDialog = DialogStep.COMPLETE }
            )
        }
        DialogStep.COMPLETE -> {
            CompleteDialog( // 받는 값으로 바꾸기
                currentWaitCount = 3,
                waitingNumber = 6,
                peopleCount = 2,
                phoneNumber = "010-1234-5678",
                storeName = "주막이름",
                onClose = {
                    currentDialog = DialogStep.NONE
                }
            )
        }

        DialogStep.NONE -> {}
    }
}

@Composable
fun WaitingItemRow(item: WaitingItem) {
    var showConfirmDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3D3D3D))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.tableIndex,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.width(30.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // 테이블 번호
            Text(
                text = "${item.tableNumber}",
                color = Color(0xFF60D160),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${item.peopleCount}명",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        // 전화번호
        Text(
            text = item.phoneNumber,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        // 메시지 전송 버튼
        Button(
            onClick = { // 메시지 전송 api
                showConfirmDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60D160)),
            modifier = Modifier
                .width(100.dp)
                .padding(end = 8.dp)
        ) {
            Text(text = "메시지 전송", fontSize = 12.sp, color = Color.Black)
        }

        // 삭제 버튼
        Button(
            onClick = { }, // 삭제 api
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            modifier = Modifier.width(60.dp)
        ) {
            Text(text = "삭제", fontSize = 12.sp, color = Color.White)
        }

        if (showConfirmDialog) {
            ConfirmMessageDialog(
                onDismiss = { showConfirmDialog = false },
                onConfirm = {
                    // 실제 메시지 전송 로직
                    sendMessageApi(item)  // 예: 네트워크 호출, etc.

                    // 다이얼로그 닫기
                    showConfirmDialog = false
                }
            )
        }
    }
}

fun sendMessageApi(item: WaitingItem) {
    // 예: println("메시지 전송: ${item.phoneNumber}")
}

@Composable
fun PeopleSelectDialog(
    onDismiss: () -> Unit,
    onNext: () -> Unit
) {
    // 예: 인원 수 상태
    var peopleCount by remember { mutableStateOf(1) }

    Dialog(onDismissRequest = { onDismiss() }) {
        // 다이얼로그 콘텐츠 배경 (회색)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFF4A4A4A))
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 상단: "인원을 선택해주세요." + X 버튼
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "인원을 선택해주세요.",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { onDismiss() }) {
                        Text("X", color = Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 가운데: - peopleCount +
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        if (peopleCount > 1) peopleCount--
                    }) {
                        Text("-", color = Color.White)
                    }

                    Text(
                        text = "${peopleCount}명",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Button(onClick = {
                        peopleCount++
                    }) {
                        Text("+", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 하단: 초록색 버튼
                Button(
                    onClick = {
                        // 인원 수 저장
                        onNext()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF60D160)
                    )
                ) {
                    Text("확인", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun PhoneInputDialog(
    onDismiss: () -> Unit,
    onNext: () -> Unit
) { // 인원 수 저장 및 전번 저장 후 한 번에 서버로 데이터 전송
    // ...
    // 전화번호 상태
    var phoneNumber by remember { mutableStateOf("010-") }

    Dialog(onDismissRequest = { onDismiss() }) {
        // 다이얼로그 전체 배경 (회색)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFF4A4A4A))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 상단 영역: "전화번호를 입력해주세요." 타이틀 + 닫기(X)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "전화번호를 입력해주세요.",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { onDismiss() }) {
                        Text("X", color = Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 현재 입력 중인 번호 표시
                Text(
                    text = phoneNumber,
                    color = Color.White,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 숫자 키패드 (3x4)
                val keypad = listOf(
                    listOf("1","2","3"),
                    listOf("4","5","6"),
                    listOf("7","8","9"),
                    listOf("↺","0","←")
                )
                keypad.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (key in row) {
                            Button(
                                onClick = {
                                    handleKeyInput(key, phoneNumber) {
                                        phoneNumber = it
                                    }
                                },
                                modifier = Modifier.size(70.dp)
                            ) {
                                Text(key, color = Color.White, fontSize = 20.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 하단 "다음" 버튼
                Button(
                    onClick = {
                        // TODO: phoneNumber 저장/전송 로직
                        onNext()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60D160))
                ) {
                    Text("다음", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}

/**
 * 숫자 키패드 한 버튼을 눌렀을 때의 처리
 * - 숫자: phoneNumber 에 추가
 * - '←' : 마지막 문자 삭제
 * - '↺' : "010-" 으로 리셋
 */
fun handleKeyInput(
    key: String,
    current: String,
    onUpdate: (String) -> Unit
) {
    when (key) {
        in listOf("0","1","2","3","4","5","6","7","8","9") -> {
            onUpdate(current + key)
        }
        "←" -> {
            if (current.isNotEmpty()) {
                onUpdate(current.dropLast(1))
            }
        }
        "↺" -> {
            onUpdate("010-")
        }
    }
}

@Composable
fun CompleteDialog(
    currentWaitCount: Int, // 예: "현재 대기 3 팀"
    waitingNumber: Int,    // 예: "대기 번호 6 번"
    peopleCount: Int,      // 예: "3명"
    phoneNumber: String,   // 예: "010-0000-0000"
    storeName: String = "주막이름",
    onClose: () -> Unit
) {
    Dialog(onDismissRequest = { onClose() }) {
        // 다이얼로그 배경
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFF4A4A4A)) // 어두운 회색
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 상단 (타이틀 + 닫기)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "대기 등록 완료",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // 오른쪽 상단 '완료' 버튼
                    TextButton(onClick = { onClose() }) {
                        Text("완료", color = Color(0xFF60D160))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 안내 문구
                Text(
                    text = "대기 등록이 완료되었습니다.\n문자 발송 시 n분 이내로 방문해주세요.",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 초록색 카드(가게 이름, 현재 대기, 대기 번호, 인원, 전화번호 등)
                Box(
                    modifier = Modifier
                        .background(Color(0xFF60D160)) // 초록색
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // 상단에 '주막이름'
                        Text(
                            text = storeName,
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // "현재 대기 3 팀"
                        Text(
                            text = "현재 대기 $currentWaitCount 팀",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // "대기 번호 6 번"
                        Text(
                            text = "대기 번호 $waitingNumber 번",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // 인원수 + 전화번호
                        Text(
                            text = "${peopleCount}명",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                        Text(
                            text = phoneNumber,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmMessageDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        // 다이얼로그 기본 배경
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f) // 대략 80% 너비
                .background(Color(0xFF4A4A4A))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 질문 문구
                Text(
                    text = "메시지를 전송하시겠습니까?",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼들 (취소, 메시지 전송)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // 취소 버튼
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("취소", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    // 메시지 전송 버튼
                    Button(
                        onClick = { onConfirm() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60D160)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("메시지 전송", color = Color.Black)
                    }
                }
            }
        }
    }
}

