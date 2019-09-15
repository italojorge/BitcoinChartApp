package br.com.db1.data.repository.remote

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.BitcoinChart

interface BitcoinChartRemoteDataSource {

    suspend fun getBitcoinChart(): Either<BitcoinChart, Throwable>

}