package br.com.db1.domain.interactor.bitcoinLastValue

import br.com.db1.domain.core.Either
import br.com.db1.domain.interactor.factory.BitcoinLastValueFactory
import br.com.db1.domain.model.BitcoinLastValue
import br.com.db1.domain.repository.BitcoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetBitcoinLastValueUseCaseTest {
    private val bitcoinRepository: BitcoinRepository = mockk()

    private lateinit var getBitcoinLastValueUseCase: GetBitcoinLastValueUseCase

    @Before
    fun `before tests`() {
        getBitcoinLastValueUseCase = GetBitcoinLastValueUseCase(
            bitcoinRepository, scope = CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Test
    fun `get bitcoin last value use case return repository result when value isn't zero`() =
        runBlocking {
            val bitcoinLastValue = BitcoinLastValueFactory.makeData()

            val expected = Either.Success(bitcoinLastValue)

            stubGetBitcoinLastValueSuccess(bitcoinLastValue)
            val result = getBitcoinLastValueUseCase.run(null)

            assertEquals(expected, result)
        }

    @Test
    fun `get bitcoin last value use case return repository result when repository returns error`() =
        runBlocking {
            val error = RuntimeException()

            val expected = Either.Failure(error)

            stubGetBitcoinLastValueFailure(error)
            val result = getBitcoinLastValueUseCase.run(null)

            assertEquals(expected, result)
        }

    @Test
    fun `get bitcoin last value use case return failure when bitcoin value is zero`() =
        runBlocking {
            val bitcoinLastValue = BitcoinLastValueFactory.makeDataWithZeroValue()

            stubGetBitcoinLastValueSuccess(bitcoinLastValue)
            val result = getBitcoinLastValueUseCase.run(null)

            assertTrue { result is Either.Failure }
        }

    private fun stubGetBitcoinLastValueSuccess(bitcoinLastValue: BitcoinLastValue) = runBlocking {
        coEvery { bitcoinRepository.getBitcoinLastValue() } returns Either.Success(bitcoinLastValue)
    }

    private fun stubGetBitcoinLastValueFailure(failure: Throwable) = runBlocking {
        coEvery { bitcoinRepository.getBitcoinLastValue() } returns Either.Failure(failure)
    }
}