package br.com.db1.data_local

import android.content.Context
import androidx.room.Room
import br.com.db1.data.repository.local.BitcoinLocalDataSource
import br.com.db1.data_local.db.BitcoinDatabase
import br.com.db1.data_local.mapper.BitcoinChartMapper
import br.com.db1.data_local.mapper.BitcoinLastValueMapper
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue

class BitcoinLocalDataSourceImpl(context: Context) : BitcoinLocalDataSource {
    private val database: BitcoinDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            BitcoinDatabase::class.java,
            "Bitcoin"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private val bitcoinChartDao = database.bitcoinChartDao()
    private val bitcoinLastValueDao = database.bitcoinLastValueDao()


    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> =
        Either.Success(BitcoinChartMapper.toDomain(bitcoinChartDao.getBitcoinChart()))

    override suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable> =
        Either.Success(BitcoinLastValueMapper.toDomain(bitcoinLastValueDao.getLastBitcoinValue()))

    override suspend fun insertBitcoinChart(bitcoinChart: BitcoinChart) {
        database.bitcoinChartDao().insertAll(
            BitcoinChartMapper.fromDomain(
                bitcoinChart
            )
        )
    }

    override suspend fun insertBitcoinLastValue(bitcoinLastValue: BitcoinLastValue) {
        database.bitcoinLastValueDao().insert(
            BitcoinLastValueMapper.fromDomain(bitcoinLastValue)
        )
    }
}