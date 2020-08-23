package br.com.db1.data_remote.factory

import br.com.db1.data_remote.factory.PrimitiveDataFactory.makeDumbDouble
import br.com.db1.data_remote.factory.PrimitiveDataFactory.makeDumbInt
import br.com.db1.data_remote.model.BitcoinDayValueResponse

object BitcoinDayValueResponseFactory : ResponseFactory<BitcoinDayValueResponse> {
    override fun dumbInstance(): BitcoinDayValueResponse {
        return BitcoinDayValueResponse(
            date = makeDumbInt(),
            value = makeDumbDouble()
        )
    }
}
