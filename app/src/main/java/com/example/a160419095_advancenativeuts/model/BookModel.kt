package com.example.a160419095_advancenativeuts.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Book(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "writer")
    var writer:String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate:String,
    @ColumnInfo(name = "stock")
    var stock:String,
    @SerializedName("photo_url")
    var photoUrl:String
){
    @PrimaryKey(autoGenerate = true)
    var bookId: Int = 0
}