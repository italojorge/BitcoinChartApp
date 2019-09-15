package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinLastValueResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BitcoinLastValueWebService {
    @GET(BITCOIN_TICKER_PATH)
    fun getBitcoinLastValue(): Deferred<BitcoinLastValueResponse>
}

const val BITCOIN_TICKER_PATH = "ticker"