package br.com.db1.data_remote.mapper

import br.com.db1.data_remote.model.BitcoinLastValueResponse
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.utils.DOUBLE_ZERO_VALUE

object BitcoinLastValueRemoteMapper: DataRemoteMapper<BitcoinLastValueResponse,BitcoinLastValue>() {
    override fun toDomain(data: BitcoinLastValueResponse): BitcoinLastValue {
        return BitcoinLastValue(data.USD?.last ?: DOUBLE_ZERO_VALUE)
    }
}