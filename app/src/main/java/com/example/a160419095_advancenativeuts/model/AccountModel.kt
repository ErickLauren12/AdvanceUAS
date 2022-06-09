package com.example.a160419095_advancenativeuts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "role")
    var role:String,
){
    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 0
}