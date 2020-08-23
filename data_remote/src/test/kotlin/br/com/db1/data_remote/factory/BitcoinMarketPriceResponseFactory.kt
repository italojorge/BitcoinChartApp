package br.com.db1.data_remote.factory

import br.com.db1.data_remote.factory.PrimitiveDataFactory.makeDumbString
import br.com.db1.data_remote.model.BitcoinMarketPriceResponse

object BitcoinMarketPriceResponseFactory : ResponseFactory<BitcoinMarketPriceResponse> {
    override fun dumbInstance(): BitcoinMarketPriceResponse =
        BitcoinMarketPriceResponse(
            description = makeDumbString(),
            name = makeDumbString(),
            period = makeDumbString(),
            status = makeDumbString(),
            unit = makeDumbString(),
            values = BitcoinDayValueResponseFactory.makeDumbList()
        )
}
