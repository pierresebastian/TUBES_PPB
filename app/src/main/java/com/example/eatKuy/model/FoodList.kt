package com.example.eatKuy.model

import com.google.gson.annotations.SerializedName

class FoodList {
    @SerializedName("results")
    var listOfFood : ArrayList<Food> = arrayListOf()
}