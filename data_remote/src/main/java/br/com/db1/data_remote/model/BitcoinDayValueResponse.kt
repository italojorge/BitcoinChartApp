package br.com.db1.data_remote.model

import com.google.gson.annotations.SerializedName

data class BitcoinDayValueResponse(
    @SerializedName("x")
    val date: Int?,
    @SerializedName("y")
    val value: Double?
)
