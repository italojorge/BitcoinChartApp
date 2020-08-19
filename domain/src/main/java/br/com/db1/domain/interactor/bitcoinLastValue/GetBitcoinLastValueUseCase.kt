package br.com.db1.domain.interactor.bitcoinLastValue

import br.com.db1.domain.core.Either
import br.com.db1.domain.core.UseCase
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.repository.BitcoinRepository
import br.com.db1.domain.utils.DOUBLE_ZERO_VALUE
import kotlinx.coroutines.CoroutineScope

class GetBitcoinLastValueUseCase(
    private val bitcoinRepository: BitcoinRepository,
    scope: CoroutineScope
) : UseCase<BitcoinLastValue, Unit>(scope) {
    override suspend fun run(params: Unit?): Either<BitcoinLastValue, Throwable> {
        return when (val result = bitcoinRepository.getBitcoinLastValue()) {
            is Either.Success -> checkLastValueIsZero(result)
            else -> result
        }
    }

    private fun checkLastValueIsZero(
        result: Either.Success<BitcoinLastValue>
    ): Either<BitcoinLastValue, Throwable> {
        return if (result.data.lastValue == DOUBLE_ZERO_VALUE) Either.Failure(Throwable())
        else result
    }
}
