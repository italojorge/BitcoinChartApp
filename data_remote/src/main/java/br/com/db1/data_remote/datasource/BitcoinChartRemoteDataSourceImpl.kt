package br.com.db1.data_remote.datasource

import br.com.db1.data.repository.remote.BitcoinChartRemoteDataSource
import br.com.db1.data_remote.mapper.BitcoinChartRemoteMapper
import br.com.db1.data_remote.service.BitcoinChartsWebService
import br.com.db1.data_remote.utils.requestWrapper
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart

class BitcoinChartRemoteDataSourceImpl(
    private val bitcoinChartsWebService: BitcoinChartsWebService
) : BitcoinChartRemoteDataSource {
    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> =
        requestWrapper {
            BitcoinChartRemoteMapper.toDomain(
                bitcoinChartsWebService.getThirtyDayBitcoinChart().await()
            )
        }

}