package br.com.db1.data.datastore

import br.com.db1.data.repository.remote.BitcoinRemoteDataSource
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.repository.BitcoinRepository

class BitcoinRepositoryImpl(
    private val bitcoinRemoteDataSource: BitcoinRemoteDataSource
) : BitcoinRepository {
    override suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable> =
        bitcoinRemoteDataSource.getBitcoinLastValue()

    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> =
        bitcoinRemoteDataSource.getBitcoinChart()

}