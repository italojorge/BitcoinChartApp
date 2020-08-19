package br.com.db1.data_local

import android.content.Context
import androidx.room.Room
import br.com.db1.data.repository.local.BitcoinLocalDataSource
import br.com.db1.data_local.db.BitcoinDatabase
import br.com.db1.data_local.entities.BitcoinLastValueEntity
import br.com.db1.data_local.mapper.BitcoinChartMapper
import br.com.db1.data_local.mapper.BitcoinLastValueMapper
import br.com.db1.data_local.utils.DATABASE_NAME
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.utils.DOUBLE_ZERO_VALUE

class BitcoinLocalDataSourceImpl(context: Context) : BitcoinLocalDataSource {
    private val database: BitcoinDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            BitcoinDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private val bitcoinChartDao = database.bitcoinChartDao()
    private val bitcoinLastValueDao = database.bitcoinLastValueDao()

    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> {
        val result = BitcoinChartMapper.toDomain(bitcoinChartDao.getBitcoinChart())
        return Either.Success(result)
    }

    override suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable> {
        val result = BitcoinLastValueMapper.toDomain(
            bitcoinLastValueDao.getLastBitcoinValue()
                ?: BitcoinLastValueEntity(DOUBLE_ZERO_VALUE)
        )
        return Either.Success(result)
    }

    override suspend fun updateBitcoinChart(bitcoinChart: BitcoinChart) {
        database.bitcoinChartDao().updateBitcoinChart(
            BitcoinChartMapper.fromDomain(bitcoinChart)
        )
    }

    override suspend fun updateBitcoinLastValue(bitcoinLastValue: BitcoinLastValue) {
        database.bitcoinLastValueDao().updateBitcoinLastValue(
            BitcoinLastValueMapper.fromDomain(bitcoinLastValue)
        )
    }
}