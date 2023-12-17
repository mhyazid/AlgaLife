package com.example.algainvest.EntityRes

import java.io.Serializable


data class AuthSignUpRes(
    val telephone:String,
    val email: String,
    val role: String,
    val name:String,
):Serializable
