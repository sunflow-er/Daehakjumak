package com.masonk.daehakjumak.remote.api

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import com.masonk.daehakjumak.data.model.CreateWaitingRequest
import com.masonk.daehakjumak.data.model.CreateWaitingResponse


interface WaitingApi { // Interceptor 만들어서 Header : Bearer 붙이기
    @POST("/api/waiting/create")
    suspend fun createWaiting(
        @Header("Authorization") bearer: String,          // 예:  Bearer {accessToken}
        @Body body: CreateWaitingRequest
    ): CreateWaitingResponse
}