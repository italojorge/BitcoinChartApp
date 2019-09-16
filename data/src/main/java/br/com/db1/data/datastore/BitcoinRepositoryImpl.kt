package br.com.db1.data.datastore

import br.com.db1.data.repository.local.BitcoinLocalDataSource
import br.com.db1.data.repository.remote.BitcoinRemoteDataSource
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.repository.BitcoinRepository

class BitcoinRepositoryImpl(
    private val bitcoinRemoteDataSource: BitcoinRemoteDataSource,
    private val bitcoinLocalDataSource: BitcoinLocalDataSource
) : BitcoinRepository {
    override suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable> {
        return when (val result = bitcoinRemoteDataSource.getBitcoinLastValue()) {
            is Either.Success -> {
                bitcoinLocalDataSource.updateBitcoinLastValue(result.b)
                bitcoinLocalDataSource.getBitcoinLastValue()
            }
            is Either.Failure -> {
                bitcoinLocalDataSource.getBitcoinLastValue()
            }
        }
    }

    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> {
        return when (val result = bitcoinRemoteDataSource.getBitcoinChart()) {
            is Either.Success -> {
                bitcoinLocalDataSource.updateBitcoinChart(result.b)
                bitcoinLocalDataSource.getBitcoinChart()
            }
            is Either.Failure -> {
                bitcoinLocalDataSource.getBitcoinChart()
            }
        }
    }
}