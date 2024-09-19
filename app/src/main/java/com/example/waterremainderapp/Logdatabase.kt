package com.example.waterremainderapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Logs::class], version = 1)
abstract class Logdatabase:RoomDatabase() {
    abstract fun logsDao(): LogsDao

    companion object {
        lateinit var database: Logdatabase
        fun createDatabases(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Logdatabase::class.java,
                "app_database"
            ).build()

    }
}