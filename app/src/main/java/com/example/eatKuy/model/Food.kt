package com.example.eatKuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
//    @SerializedName(value = "author")
    @SerializedName(value = "title")
    var name: String,
    @SerializedName(value = "href")
    var href: String,
    @SerializedName(value = "ingredients")
    var ingredient: String,
    @SerializedName(value = "thumbnail")
    var image: String
): Parcelable