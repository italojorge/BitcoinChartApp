package br.com.db1.data_remote.factory

import br.com.db1.data_remote.factory.PrimitiveDataFactory.makeDumbDouble
import br.com.db1.data_remote.model.USDResponse

object USDResponseFactory : ResponseFactory<USDResponse> {
    override fun dumbInstance(): USDResponse {
        return USDResponse(
            lastBitcoinValue = makeDumbDouble()
        )
    }
}
