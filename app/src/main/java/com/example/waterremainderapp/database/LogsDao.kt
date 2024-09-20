package com.example.waterremainderapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogsDao {

    @Insert
    suspend fun insertLogs(logs: Logs)

    @Query("SELECT * FROM waterRecordLogs ORDER BY time DESC")
    fun getAllLogs(): Flow<List<Logs>>
}