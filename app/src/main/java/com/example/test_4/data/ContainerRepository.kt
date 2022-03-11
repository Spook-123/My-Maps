package com.example.test_4.data

import androidx.lifecycle.LiveData

class ContainerRepository (private val mapsDao:DatabaseMapDao){

    val readAllData:LiveData<List<Place>> = mapsDao.readAllData()

    suspend fun insert(maps:Place) {
        mapsDao.insert(maps)
    }

    suspend fun delete(maps:Place) {
        mapsDao.delete(maps)
    }

}