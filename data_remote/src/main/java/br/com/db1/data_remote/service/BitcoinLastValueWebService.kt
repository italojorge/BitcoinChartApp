package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinLastValueResponse
import retrofit2.http.GET

interface BitcoinLastValueWebService {
    private companion object {
        const val BITCOIN_TICKER_PATH = "ticker"
    }

    @GET(BITCOIN_TICKER_PATH)
    suspend fun getBitcoinLastValue(): BitcoinLastValueResponse
}
