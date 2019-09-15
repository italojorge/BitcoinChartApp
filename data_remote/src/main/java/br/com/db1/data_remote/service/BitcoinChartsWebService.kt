package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinMarketPriceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface BitcoinChartsWebService {
    @GET(THIRTY_DAY_MARKET_PRICE_PATH)
    fun getBitcoinChart(): Deferred<BitcoinMarketPriceResponse>
}

const val THIRTY_DAY_MARKET_PRICE_PATH = "market-price?timespan=30days"