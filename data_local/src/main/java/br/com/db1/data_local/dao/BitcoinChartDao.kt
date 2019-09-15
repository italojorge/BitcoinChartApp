package br.com.db1.data_local.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.db1.data_local.entities.BITCOIN_CHART_TABLE_NAME
import br.com.db1.data_local.entities.BitcoinDayValueEntity

@Dao
interface BitcoinChartDao : BaseDao<BitcoinDayValueEntity> {
    companion object {
        private const val QUERY_GET_BITCOIN_CHART =
            "SELECT * FROM $BITCOIN_CHART_TABLE_NAME ORDER BY $BITCOIN_CHART_TABLE_NAME.date DESC"
    }

    @Query(QUERY_GET_BITCOIN_CHART)
    suspend fun getBitcoinChart(): List<BitcoinDayValueEntity>
}
