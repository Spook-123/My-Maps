package com.example.test_4.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DatabaseMapDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data:Place)

    @Delete
    suspend fun delete(data:Place)

    @Query("SELECT * FROM content_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Place>>

}