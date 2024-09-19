package com.example.waterremainderapp

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface LogsDao {

    @Insert
    suspend fun insertLogs(logs:Logs)

    @Query("SELECT * FROM waterRecordLogs ORDER BY time DESC")
    fun getAllLogs(): MutableStateFlow<List<Logs>>
}