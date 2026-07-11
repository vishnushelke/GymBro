package com.pamu.gymbro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pamu.gymbro.data.local.entity.ProgressEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM progress_entries ORDER BY date DESC")
    fun getAllProgressEntries(): Flow<List<ProgressEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProgressEntry(entry: ProgressEntryEntity)

    @Query("DELETE FROM progress_entries WHERE id = :id")
    fun deleteProgressEntry(id: Long)
}
