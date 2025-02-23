package com.smilegate.Easel.domain.api

import com.smilegate.Easel.domain.model.auth.EmailAuth
import com.smilegate.Easel.domain.model.auth.EmailRequest
import com.smilegate.Easel.domain.model.auth.EmailResponse
import com.smilegate.Easel.domain.model.join.JoinRequest
import com.smilegate.Easel.domain.model.join.TemporaryJoinRequest
import com.smilegate.Easel.domain.model.join.VerifyUsernameRequest
import com.smilegate.Easel.domain.model.join.VerifyUsernameResponse
import com.smilegate.Easel.domain.model.login.LoginRequest
import com.smilegate.Easel.domain.model.login.LoginResponse
import com.smilegate.Easel.domain.model.user.UserProfileResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth")
    suspend fun sendCode(@Body request: EmailAuth): Response<ResponseBody>

    @POST("api/users/verify-email")
    suspend fun verifyEmail(@Body request: EmailRequest): Response<EmailResponse>

    @POST("api/users/temporary-join")
    suspend fun temporaryJoin(@Body request: TemporaryJoinRequest): Response<Unit>

    @POST("api/users/verify-username")
    suspend fun verifyUsername(@Body request: VerifyUsernameRequest): Response<VerifyUsernameResponse>

    @POST("api/users/join")
    suspend fun joinUser(@Body joinRequest: JoinRequest): Response<Unit>

    @POST("api/auth/mobile")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/users/me")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserProfileResponse>

}
