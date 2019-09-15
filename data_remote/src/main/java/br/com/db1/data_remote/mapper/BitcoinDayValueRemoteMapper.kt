package br.com.db1.data_remote.mapper

import br.com.db1.data_remote.model.BitcoinDayValueResponse
import br.com.db1.domain.model.BitcoinDayValue

object BitcoinDayValueRemoteMapper : DataRemoteMapper<BitcoinDayValueResponse, BitcoinDayValue>() {
    override fun toDomain(data: BitcoinDayValueResponse): BitcoinDayValue {
        return BitcoinDayValue(
            date = data.x ?: 0,
            bitcoinValue = data.y ?: 0.0
        )
    }
}