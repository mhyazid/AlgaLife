package com.example.algainvest.EntityRes

import java.io.Serializable

data class CountStat(
    val perPage:Int,
    val totalItems:Int,
    val page:Int,
):Serializable
