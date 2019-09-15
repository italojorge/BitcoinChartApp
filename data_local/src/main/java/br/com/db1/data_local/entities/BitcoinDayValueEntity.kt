package br.com.db1.data_local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BITCOIN_CHART_TABLE_NAME)
data class BitcoinDayValueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val x: Int?,
    val y: Double?
)

const val BITCOIN_CHART_TABLE_NAME = "bitcoinChart"
