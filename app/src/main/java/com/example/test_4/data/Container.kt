package com.example.test_4.data

import android.content.Context
import androidx.room.*


@Database(entities = [Place::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Container: RoomDatabase() {

    abstract fun mapsDao():DatabaseMapDao

    companion object {

        @Volatile
        private var INSTANCE:Container? = null
        fun getDatabase(context: Context):Container {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,Container::class.java,"contain_database").build()
                INSTANCE = instance
                return instance

            }

        }
    }


}