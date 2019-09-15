package br.com.db1.domain.repository

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue

interface BitcoinRepository {

    suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable>

    suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable>
}