package br.com.db1.domain.interactor.factory

import br.com.db1.domain.interactor.factory.DataFactory.Companion.randomDouble
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.utils.DOUBLE_ZERO_VALUE

object BitcoinLastValueFactory : DataFactory<BitcoinLastValue> {
    override fun makeData() = BitcoinLastValue(
        lastValue = randomDouble()
    )

    fun makeDataWithZeroValue() = BitcoinLastValue(
        lastValue = DOUBLE_ZERO_VALUE
    )
}
