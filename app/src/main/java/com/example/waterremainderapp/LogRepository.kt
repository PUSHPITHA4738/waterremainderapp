package com.example.waterremainderapp

class LogRepository(private val logsDao: LogsDao) {
    fun getAllLogs()=logsDao.getAllLogs()

    suspend fun insertLogs(logs:Logs){
        logsDao.insertLogs(logs)
    }

}