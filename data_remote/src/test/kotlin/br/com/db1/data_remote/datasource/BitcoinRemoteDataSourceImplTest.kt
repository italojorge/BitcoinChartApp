package br.com.db1.data_remote.datasource

import br.com.db1.data.repository.remote.BitcoinRemoteDataSource
import br.com.db1.data_remote.factory.BitcoinLastValueResponseFactory
import br.com.db1.data_remote.factory.BitcoinMarketPriceResponseFactory
import br.com.db1.data_remote.mapper.BitcoinChartRemoteMapper
import br.com.db1.data_remote.mapper.BitcoinLastValueRemoteMapper
import br.com.db1.data_remote.service.BitcoinChartsWebService
import br.com.db1.data_remote.service.BitcoinLastValueWebService
import br.com.db1.data_remote.utils.ServerError
import br.com.db1.domain.core.Either
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class BitcoinRemoteDataSourceImplTest {
    private val bitcoinChartsWebService: BitcoinChartsWebService = mockk()
    private val bitcoinLastValueWebService: BitcoinLastValueWebService = mockk()

    private lateinit var bitcoinRemoteDataSource: BitcoinRemoteDataSource

    @Before
    fun `before tests`() {
        bitcoinRemoteDataSource = BitcoinRemoteDataSourceImpl(
            bitcoinChartsWebService, bitcoinLastValueWebService
        )
    }

    @Test
    fun `should getBitcoinLastValue return success with mapped data when service return success`() =
        runBlocking {
            val bitcoinLastValueResponse = BitcoinLastValueResponseFactory.dumbInstance()

            coEvery {
                bitcoinLastValueWebService.getBitcoinLastValue()
            } returns bitcoinLastValueResponse

            val expected = Either.Success(
                BitcoinLastValueRemoteMapper.toDomain(bitcoinLastValueResponse)
            )

            val result = bitcoinRemoteDataSource.getBitcoinLastValue()

            assertEquals(expected, result)
        }

    @Test
    fun `should getBitcoinLastValue return generic failure when service return failure`() =
        runBlocking {
            val exception = IOException()

            coEvery {
                bitcoinLastValueWebService.getBitcoinLastValue()
            } throws exception

            val result = bitcoinRemoteDataSource.getBitcoinLastValue()

            assert(result is Either.Failure && result.cause is ServerError)
        }

    @Test
    fun `should getBitcoinChart return success with mapped data when service return success`() =
        runBlocking {
            val bitcoinMarketPriceResponse = BitcoinMarketPriceResponseFactory.dumbInstance()

            coEvery {
                bitcoinChartsWebService.getBitcoinChart(any())
            } returns bitcoinMarketPriceResponse

            val expected = Either.Success(
                BitcoinChartRemoteMapper.toDomain(bitcoinMarketPriceResponse)
            )

            val result = bitcoinRemoteDataSource.getBitcoinChart()

            assertEquals(expected, result)
        }

    @Test
    fun `should getBitcoinChart return generic failure when service return failure`() =
        runBlocking {
            val exception = IOException()

            coEvery {
                bitcoinChartsWebService.getBitcoinChart()
            } throws exception

            val result = bitcoinRemoteDataSource.getBitcoinChart()

            assert(result is Either.Failure && result.cause is ServerError)
        }
}