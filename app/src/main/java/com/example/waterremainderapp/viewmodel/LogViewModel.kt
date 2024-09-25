package com.example.waterremainderapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.waterremainderapp.database.LogRepository
import com.example.waterremainderapp.database.Logdatabase
import com.example.waterremainderapp.database.Logs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class LogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LogRepository
    val allLogs: Flow<List<Logs>>

    init {
        // Initialize repository
        val logDao = Logdatabase.createDatabases(application).logsDao()
        repository = LogRepository(logDao)
        allLogs = repository.getAllLogs()
    }

    fun addLog(quantity: Int, time: String) {
        viewModelScope.launch {
            repository.insertLogs(Logs(quantity = quantity, time = time))
        }
    }

    class LogsVmFactory(private val application: Application) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LogViewModel(application) as T
        }
    }
}

