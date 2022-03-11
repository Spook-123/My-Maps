package com.example.test_4.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {

    /**@TypeConverter
    fun fromMain(value:Main):Double{
        return value.latitude

    }**/
    @TypeConverter
    fun fromMainLat(value:List<Main>):String {
        return Gson().toJson(value)
    }

   @TypeConverter
   fun toMain(value: String):List<Main> {
       val listType = object: TypeToken<List<Main>>() {}.type
       return Gson().fromJson(value,listType)

   }

}