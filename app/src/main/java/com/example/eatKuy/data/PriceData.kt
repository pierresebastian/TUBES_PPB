package com.example.eatKuy.data

object PriceData {
    private val priceData = arrayOf(
        12,
        10,
        13,
        11,
        9,
        14,
        10,
        13,
        12,
        10)

    val listPriceData: ArrayList<Int>
        get() {
            val listPrice = ArrayList<Int>()
            for(element in priceData){
                listPrice.add(element)
            }
            return listPrice
        }
}