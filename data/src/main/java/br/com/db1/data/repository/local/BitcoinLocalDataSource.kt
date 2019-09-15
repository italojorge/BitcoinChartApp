package br.com.db1.data.repository.local

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue

interface BitcoinLocalDataSource {
    suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable>

    suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable>

    suspend fun insertBitcoinChart(bitcoinChart: BitcoinChart)

    suspend fun insertBitcoinLastValue(bitcoinLastValue: BitcoinLastValue)
}