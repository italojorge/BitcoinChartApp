package br.com.db1.data_local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<D> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(d: D): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<D>): List<Long>

    @Update
    fun update(d: D)

    @Update
    fun updateAll(list: List<D>)

    @Delete
    fun delete(d: D): Int

}