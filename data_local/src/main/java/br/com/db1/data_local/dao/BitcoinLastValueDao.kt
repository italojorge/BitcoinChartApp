package br.com.db1.data_local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.db1.data_local.entities.BITCOIN_CHART_TABLE_NAME
import br.com.db1.data_local.entities.BITCOIN_LAST_VALUE_TABLE_NAME
import br.com.db1.data_local.entities.BitcoinDayValueEntity
import br.com.db1.data_local.entities.BitcoinLastValueEntity

@Dao
interface BitcoinLastValueDao : BaseDao<BitcoinLastValueEntity> {
    companion object {
        private const val QUERY_GET_LAST_BITCOIN_VALUE =
            "SELECT * FROM $BITCOIN_LAST_VALUE_TABLE_NAME"

        private const val DELETE_BITCOIN_LAST_VALUE = "DELETE FROM $BITCOIN_LAST_VALUE_TABLE_NAME"
    }

    @Query(QUERY_GET_LAST_BITCOIN_VALUE)
    suspend fun getLastBitcoinValue(): BitcoinLastValueEntity?

    @Query(DELETE_BITCOIN_LAST_VALUE)
    suspend fun deleteBitcoinLastValue()

    @Transaction
    suspend fun updateBitcoinLastValue(
        bitcoinLastValueEntity: BitcoinLastValueEntity
    ) {
        deleteBitcoinLastValue()
        insert(bitcoinLastValueEntity)
    }
}
