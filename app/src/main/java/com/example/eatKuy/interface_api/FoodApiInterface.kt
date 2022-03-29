package com.example.eatKuy.interface_api

import com.example.eatKuy.model.Food
import com.example.eatKuy.model.FoodList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

const val BASE_URL = "http://www.recipepuppy.com/"

interface FoodApiInterface {
    @GET(value = "api/")
    fun getFoods() : Call<FoodList>

    companion object {
        operator fun invoke() : FoodApiInterface{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoodApiInterface::class.java)
        }
    }
}