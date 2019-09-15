package br.com.db1.data_remote.model

data class BitcoinMarketPriceResponse(
    val description: String?,
    val name: String?,
    val period: String?,
    val status: String?,
    val unit: String?,
    val values: List<BitcoinDayValueResponse>?
)