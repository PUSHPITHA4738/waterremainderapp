package com.example.waterremainderapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "waterRecordLogs")
data class Logs(
    @PrimaryKey (autoGenerate = true)
    val id:Int=0,
    val quantity:Int,
    val time:String
)
