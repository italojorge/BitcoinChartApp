package br.com.db1.domain.interactor.bitcoinChart

import br.com.db1.domain.core.Either
import br.com.db1.domain.core.UseCase
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.repository.BitcoinRepository
import kotlinx.coroutines.CoroutineScope

class GetBitcoinChartUseCase(
    private val bitcoinRepository: BitcoinRepository,
    scope: CoroutineScope
) : UseCase<BitcoinChart, Unit>(scope) {
    override suspend fun run(params: Unit?): Either<BitcoinChart, Throwable> {
        return when (val result = bitcoinRepository.getBitcoinChart()) {
            is Either.Success -> checkChartListIsEmpty(result)
            else -> result
        }
    }

    private fun checkChartListIsEmpty(
        result: Either.Success<BitcoinChart>
    ): Either<BitcoinChart, Throwable> {
        return if (result.data.chart.isNotEmpty()) result
        else Either.Failure(Throwable())
    }
}
