package br.com.db1.data.repository.remote

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue

interface BitcoinRemoteDataSource {

    suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable>

    suspend fun getBitcoinLastValue(): Either<BitcoinLastValue, Throwable>
}