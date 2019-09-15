package br.com.db1.presentation.model

data class BitcoinChartBinding (
    val chart: List<BitcoinDayValueBinding>
)

fun emptyBitcoinChartBinding() = BitcoinChartBinding(listOf())
