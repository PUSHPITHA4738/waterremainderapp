package com.example.waterremainderapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
class LogViewModel (application: Application):AndroidViewModel(application){
    private val repository: LogRepository
    val allLogs: MutableStateFlow<List<Logs>>

    init {
        // Initialize repository
        val LogDao = Logdatabase.createDatabases(application).logsDao()
        repository = LogRepository(LogDao)
         allLogs = repository.getAllLogs()
    }

    fun addLog(quantity: Int, time: Long) {
        viewModelScope.launch {
            repository.insertLogs(Logs(quantity=quantity, time = time))
        }
    }

class LogsVmFactory (private val application: Application):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogViewModel(application) as T
    }

}
}

