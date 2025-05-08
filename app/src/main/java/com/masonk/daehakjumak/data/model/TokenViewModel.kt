package com.masonk.daehakjumak.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TokenViewModel : ViewModel() {

    // 예: 서버에서 가져온 TokenResponseDto를 저장할 상태
    var tokenResponse: KakaoLoginResponse? by mutableStateOf(null)
        private set

    // 에러 메시지나 로딩 상태 등도 필요하다면 추가
    var errorMessage: String? by mutableStateOf(null)
        private set

    /**
     * 토큰 요청 API (가상 메서드)
     */
    fun fetchToken() {
        // 실제는 Retrofit/Ktor 등을 사용:
        // e.g. retrofitService.fetchToken() ...

        viewModelScope.launch {
            try {
                // 예시로 바로 Response DTO를 만들어 반환한다고 가정
                val dummyResponse = KakaoLoginResponse(
                    code = 0,
                    message = "Success",
                    value = TokenValueDto(
                        accessToken = "dummyAccess",
                        refreshToken = "dummyRefresh",
                        expiresIn = 3600
                    )
                )
                // 성공 시 상태 업데이트
                tokenResponse = dummyResponse
            } catch (e: Exception) {
                errorMessage = "API 실패: ${e.localizedMessage}"
            }
        }
    }

    /**
     * AccessTokenRequestDto를 보낼 때
     */
    fun sendAccessToken(accessToken: String) {
        viewModelScope.launch {
            try {
                val requestBody = AccessTokenRequestDto(accessToken)
                // 예: retrofitService.sendAccessToken(requestBody)
                // 결과 처리...
            } catch (e: Exception) {
                errorMessage = "전송 실패: ${e.localizedMessage}"
            }
        }
    }
}
