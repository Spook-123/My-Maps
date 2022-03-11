package com.example.test_4.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "content_table")
data class Place(
    val titleMain:String,
    val creatorName:String,
    val mainView: List<Main>
):Serializable {

    @PrimaryKey(autoGenerate = true) var id = 0
}