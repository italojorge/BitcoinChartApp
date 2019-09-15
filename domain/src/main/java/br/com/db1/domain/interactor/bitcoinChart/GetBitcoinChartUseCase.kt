package br.com.db1.domain.interactor.bitcoinChart

import br.com.db1.domain.core.Either
import br.com.db1.domain.core.UseCase
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.repository.BitcoinChartRepository
import kotlinx.coroutines.CoroutineScope

class GetBitcoinChartUseCase(
    private val bitcoinChartRepository: BitcoinChartRepository,
    scope: CoroutineScope
) : UseCase<BitcoinChart, Unit>(scope) {
    override suspend fun run(params: Unit?): Either<BitcoinChart, Throwable> {
        return bitcoinChartRepository.getBitcoinChart()
    }
}