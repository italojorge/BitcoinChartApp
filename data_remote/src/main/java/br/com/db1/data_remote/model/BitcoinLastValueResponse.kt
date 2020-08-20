package br.com.db1.data_remote.model

import com.google.gson.annotations.SerializedName

data class BitcoinLastValueResponse(
    @SerializedName("USD")
    val usdResponse: USDResponse?
)