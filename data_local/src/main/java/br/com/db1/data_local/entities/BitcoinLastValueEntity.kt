package br.com.db1.data_local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BITCOIN_LAST_VALUE_TABLE_NAME)
data class BitcoinLastValueEntity(
    @PrimaryKey(autoGenerate = false) val lastValue: Double
)

const val BITCOIN_LAST_VALUE_TABLE_NAME = "bitcoinLastValue"
