package com.example.watermonitorapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watermonitorapp.data.entity.WaterControl
import kotlinx.coroutines.flow.Flow


@Dao
interface WaterControlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterItem(waterControl: WaterControl)

    @Delete
    suspend fun deleteWaterItem(waterControl: WaterControl)

    @Query("SELECT * FROM WaterControl")
    fun getAllWaterItems(): Flow<List<WaterControl>>

    @Query("SELECT * FROM WaterControl WHERE id LIKE :id")
    fun getWaterItemById(id: Int): Flow<WaterControl>
}
