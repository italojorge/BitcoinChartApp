package br.com.db1.data_local.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.db1.data_local.entities.BITCOIN_LAST_VALUE_TABLE_NAME
import br.com.db1.data_local.entities.BitcoinLastValueEntity

@Dao
interface BitcoinLastValueDao : BaseDao<BitcoinLastValueEntity> {
    companion object {
        private const val QUERY_GET_LAST_BITCOIN_VALUE =
            "SELECT * FROM $BITCOIN_LAST_VALUE_TABLE_NAME"
    }

    @Query(QUERY_GET_LAST_BITCOIN_VALUE)
    suspend fun getLastBitcoinValue(): BitcoinLastValueEntity
}
