package com.pamu.gymbro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pamu.gymbro.data.local.entity.DailyStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyStatsDao {
    @Query("SELECT * FROM daily_stats WHERE date = :date")
    fun getStatsForDate(date: String): Flow<DailyStatsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStats(stats: DailyStatsEntity)

    @Query("UPDATE daily_stats SET waterIntakeMl = waterIntakeMl + :amount WHERE date = :date")
    fun updateWaterIntake(date: String, amount: Int)
}
