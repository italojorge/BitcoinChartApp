package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinLastValueResponse
import br.com.db1.data_remote.model.BitcoinMarketPriceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BitcoinChartsWebService {
    private companion object {
        const val MARKET_PRICE_PATH = "market-price"
        const val BITCOIN_TICKER_PATH = "ticker"

        const val TIME_SPAN_QUERY = "timespan"

        const val TIMESPAN_DEFAULT_VALUE = "30days"
    }

    @GET(MARKET_PRICE_PATH)
    suspend fun getBitcoinChart(
        @Query(TIME_SPAN_QUERY) daysPath: String = TIMESPAN_DEFAULT_VALUE
    ): BitcoinMarketPriceResponse

    @GET(BITCOIN_TICKER_PATH)
    suspend fun getBitcoinLastValue(): BitcoinLastValueResponse
}


