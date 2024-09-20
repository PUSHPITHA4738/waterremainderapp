package com.example.waterremainderapp.database

class LogRepository(private val logsDao: LogsDao) {
    fun getAllLogs()=logsDao.getAllLogs()

    suspend fun insertLogs(logs: Logs){
        logsDao.insertLogs(logs)
    }

}