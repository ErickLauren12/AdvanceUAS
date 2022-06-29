package com.example.a160419095_advancenativeuts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaksi(
    @ColumnInfo(name = "startDate")
    var startDate:String,
    @ColumnInfo(name = "endDate")
    var endDate:String,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "image")
    var image:String,
    @ColumnInfo(name = "bookId")
    var bookId: Int,
){
    @PrimaryKey(autoGenerate = true)
    var idTransaction: Int = 0
}