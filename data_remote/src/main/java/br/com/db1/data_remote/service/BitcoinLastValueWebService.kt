package br.com.db1.data_remote.service

import br.com.db1.data_remote.model.BitcoinLastValueResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BitcoinLastValueWebService {
    @GET("ticker")
    fun getBitcoinLastValue(): Deferred<BitcoinLastValueResponse>
}