package com.example.a160419095_advancenativeuts.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    var title:String?,
    var description:String?,
    var writer:String?,
    var releaseDate:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)