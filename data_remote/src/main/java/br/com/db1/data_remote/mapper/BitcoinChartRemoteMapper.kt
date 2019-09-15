package br.com.db1.data_remote.mapper

import br.com.db1.data_remote.model.BitcoinMarketPriceResponse
import br.com.db1.domain.model.BitcoinChart

object BitcoinChartRemoteMapper : DataRemoteMapper<BitcoinMarketPriceResponse, BitcoinChart>() {
    override fun toDomain(data: BitcoinMarketPriceResponse): BitcoinChart {
        return BitcoinChart(
            data.values?.map {
                BitcoinDayValueRemoteMapper.toDomain(it)
            } ?: listOf()
        )
    }
}