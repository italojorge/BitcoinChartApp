package br.com.db1.presentation.mapper.bitcoin

import br.com.db1.domain.model.BitcoinDayValue
import br.com.db1.presentation.mapper.PresentationMapper
import br.com.db1.presentation.model.BitcoinDayValueBinding

object BitcoinDayValueMapper:PresentationMapper<BitcoinDayValueBinding,BitcoinDayValue> {
    override fun toDomain(presentation: BitcoinDayValueBinding): BitcoinDayValue =
        BitcoinDayValue(
            date = presentation.date,
            bitcoinValue = presentation.bitcoinValue
        )


    override fun fromDomain(domain: BitcoinDayValue): BitcoinDayValueBinding =
        BitcoinDayValueBinding(
            date = domain.date,
            bitcoinValue = domain.bitcoinValue
        )
}