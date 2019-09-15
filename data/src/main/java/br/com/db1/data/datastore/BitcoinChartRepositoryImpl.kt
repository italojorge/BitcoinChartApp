package br.com.db1.data.datastore

import br.com.db1.data.repository.remote.BitcoinChartRemoteDataSource
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.repository.BitcoinChartRepository

class BitcoinChartRepositoryImpl(
    private val bitcoinChatDataSource: BitcoinChartRemoteDataSource
) : BitcoinChartRepository {
    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> =
        bitcoinChatDataSource.getBitcoinChart()

}