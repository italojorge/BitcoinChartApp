package br.com.db1.data_remote.factory

import br.com.db1.data_remote.model.BitcoinLastValueResponse

object BitcoinLastValueResponseFactory : ResponseFactory<BitcoinLastValueResponse> {
    override fun dumbInstance(): BitcoinLastValueResponse {
        return BitcoinLastValueResponse(
            usdResponse = USDResponseFactory.dumbInstance()
        )
    }
}
