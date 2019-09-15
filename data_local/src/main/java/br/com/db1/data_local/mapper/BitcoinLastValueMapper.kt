package br.com.db1.data_local.mapper

import br.com.db1.data_local.entities.BitcoinLastValueEntity
import br.com.db1.domain.model.BitcoinLastValue

object BitcoinLastValueMapper:Mapper<BitcoinLastValueEntity, BitcoinLastValue> {
    override fun toDomain(entity: BitcoinLastValueEntity): BitcoinLastValue {
        return BitcoinLastValue(
            entity.lastValue
        )
    }

    override fun fromDomain(domain: BitcoinLastValue): BitcoinLastValueEntity {
        return BitcoinLastValueEntity(domain.lastValue)
    }
}