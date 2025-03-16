package com.masonk.daehakjumak.remote.api

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.masonk.daehakjumak.BuildConfig

class GlobalApplicaion : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}