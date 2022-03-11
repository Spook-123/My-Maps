package com.example.test_4.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContainerViewModel(application: Application) :AndroidViewModel(application) {

    val readAllData:LiveData<List<Place>>
    private val repository:ContainerRepository

    init {
        val dao = Container.getDatabase(application).mapsDao()
        repository = ContainerRepository(dao)
        readAllData = repository.readAllData
    }

    fun insert(maps:Place) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(maps)
        }
    }

    fun delete(maps:Place) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(maps)
        }
    }


}