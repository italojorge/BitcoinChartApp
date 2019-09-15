package br.com.db1.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.db1.data_local.dao.BitcoinChartDao
import br.com.db1.data_local.dao.BitcoinLastValueDao
import br.com.db1.data_local.entities.BitcoinDayValueEntity
import br.com.db1.data_local.entities.BitcoinLastValueEntity

@Database(
        entities = [
            BitcoinLastValueEntity::class,
            BitcoinDayValueEntity::class
        ],
        version = 1,
        exportSchema = true
)
abstract class BitcoinDatabase : RoomDatabase() {
    abstract fun bitcoinChartDao(): BitcoinChartDao
    abstract fun bitcoinLastValueDao(): BitcoinLastValueDao
}