package br.com.db1.data_remote.datasource

import br.com.db1.data.repository.remote.BitcoinRemoteDataSource
import br.com.db1.data_remote.mapper.BitcoinChartRemoteMapper
import br.com.db1.data_remote.mapper.BitcoinLastValueRemoteMapper
import br.com.db1.data_remote.service.BitcoinChartsWebService
import br.com.db1.data_remote.service.BitcoinLastValueWebService
import br.com.db1.data_remote.utils.requestWrapper
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue

class BitcoinRemoteDataSourceImpl(
    private val bitcoinChartsWebService: BitcoinChartsWebService,
    private val bitcoinLastValueWebService: BitcoinLastValueWebService
) : BitcoinRemoteDataSource {
    override suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable> =
        requestWrapper {
            BitcoinLastValueRemoteMapper.toDomain(
                bitcoinLastValueWebService.getBitcoinLastValue()
            )
        }

    override suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable> =
        requestWrapper {
            BitcoinChartRemoteMapper.toDomain(
                bitcoinChartsWebService.getBitcoinChart()
            )
        }
}
