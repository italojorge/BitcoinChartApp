package br.com.db1.presentation.mapper.bitcoin

import br.com.db1.domain.model.BitcoinChart
import br.com.db1.presentation.mapper.PresentationMapper
import br.com.db1.presentation.model.BitcoinChartBinding

object BitcoinChartMapper : PresentationMapper<BitcoinChartBinding, BitcoinChart> {
    override fun toDomain(presentation: BitcoinChartBinding): BitcoinChart =
        BitcoinChart(
            chart = presentation.chart.map {
                BitcoinDayValueMapper.toDomain(it)
            }
        )

    override fun fromDomain(domain: BitcoinChart): BitcoinChartBinding =
        BitcoinChartBinding(
            chart = domain.chart.map {
                BitcoinDayValueMapper.fromDomain(it)
            }
        )

}
