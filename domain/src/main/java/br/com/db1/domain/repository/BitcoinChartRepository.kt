package br.com.db1.domain.repository

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart

interface BitcoinChartRepository {

    suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable>
}