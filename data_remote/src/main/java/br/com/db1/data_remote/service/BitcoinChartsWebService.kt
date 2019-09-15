package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinMarketPriceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface BitcoinChartsWebService {
    companion object {
        const val timespanPath = "timespan"
        const val MARKET_PRICE_PATH = "market-price"

        const val TIMESPAN_DEFAULT_VALUE = "30days"
    }

    @GET(MARKET_PRICE_PATH)
    fun getBitcoinChart(@Query(timespanPath) daysPath: String = TIMESPAN_DEFAULT_VALUE): Deferred<BitcoinMarketPriceResponse>
}

