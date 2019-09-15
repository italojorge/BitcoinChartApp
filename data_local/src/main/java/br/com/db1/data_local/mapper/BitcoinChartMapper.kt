package br.com.db1.data_local.mapper

import br.com.db1.data_local.entities.BitcoinDayValueEntity
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinDayValue

object BitcoinChartMapper:Mapper<List<BitcoinDayValueEntity>,BitcoinChart> {
    override fun fromDomain(domain: BitcoinChart): List<BitcoinDayValueEntity> {
        return domain.chart.map {
            BitcoinDayValueEntity(
                date = it.date,
                bitcoinValue = it.bitcoinValue
            )
        }
    }

    override fun toDomain(entity: List<BitcoinDayValueEntity>): BitcoinChart {
        return BitcoinChart(
            entity.map {
                BitcoinDayValue(
                    date = it.date,
                    bitcoinValue = it.bitcoinValue
                )
            }
        )
    }

}