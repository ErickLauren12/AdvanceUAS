package com.example.a160419095_advancenativeuts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "image")
    var image:String,
    @ColumnInfo(name = "stock")
    var stock:Int,
){
    @PrimaryKey(autoGenerate = true)
    var idBook: Int = 0
}