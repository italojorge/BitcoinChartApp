package br.com.db1.domain.interactor.bitcoinLastValue

import br.com.db1.domain.core.Either
import br.com.db1.domain.core.UseCase
import br.com.db1.domain.model.BitcoinChart
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.repository.BitcoinRepository
import kotlinx.coroutines.CoroutineScope

class GetBitcoinLastValueUseCase(
    private val bitcoinRepository: BitcoinRepository,
    scope: CoroutineScope
) : UseCase<BitcoinLastValue, Unit>(scope) {
    override suspend fun run(params: Unit?): Either<BitcoinLastValue, Throwable> {
        return bitcoinRepository.getBitcoinLastValue()
    }
}