package com.example.eatKuy.model

class CartFood {
    var id : Int = 0
    var name : String = ""
    var qty : Int = 0
    var price : Int = 0
    var image : String = ""

    constructor(name: String, qty: Int, price: Int, image: String){
        this.name = name
        this.qty = qty
        this.price = price
        this.image = image
    }

    constructor(){
    }
}