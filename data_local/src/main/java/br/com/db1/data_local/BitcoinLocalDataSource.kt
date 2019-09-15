package br.com.db1.data_local

import android.content.Context
import androidx.room.Room
import br.com.db1.data_local.db.BitcoinDatabase

class BitcoinLocalDataSource(context: Context) : LocalDataSource {

    private val database: BitcoinDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            BitcoinDatabase::class.java,
            "Bitcoin"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private val bitcoinDayValueDao = database.bitcoinDayValueDao()
    private val bitcoinLastValueDao = database.bitcoinLastValueDao()

}