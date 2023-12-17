package com.example.charmrides.ApiService

import com.example.algainvest.EntityReq.AuthPassEmail
import com.example.algainvest.EntityReq.AuthSignUp
import com.example.algainvest.EntityRes.AuthSignUpRes
import com.example.algainvest.EntityRes.UserRecord
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/collections/users/auth-with-password")
    fun getUserAuth(
        @Body authPassEmail: AuthPassEmail
    ): Call<UserRecord>

    @POST("/api/collections/users/records")
    fun createUserAuth(
        @Body authSignUp: AuthSignUp
    ):Call<AuthSignUpRes>
}