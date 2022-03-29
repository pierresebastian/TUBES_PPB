package com.example.eatKuy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.eatKuy.model.CartFood
import com.example.eatKuy.R
import com.example.eatKuy.data.ImageData
import com.example.eatKuy.data.PriceData
import com.example.eatKuy.db.DataBaseHeader
import kotlinx.android.synthetic.main.activity_detail_food.*

class DetailFoodActivity: AppCompatActivity(), View.OnClickListener {

    private var listPrice: ArrayList<Int> = arrayListOf()
    private var listImg: ArrayList<String> = arrayListOf()
    private var name: String = ""
    private var ingredient: String = ""
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        val closeDetailFood: ImageView = findViewById(R.id.close_detail)
        closeDetailFood.setOnClickListener(this)

        val addToCart: Button = findViewById(R.id.btn_add_cart)
        addToCart.setOnClickListener(this)

        getPriceData()
        getImageData()
        showDetailFood()

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.close_detail -> {
                val backToMainIntent = Intent(this@DetailFoodActivity, MainMenuActivity::class.java)
                startActivity(backToMainIntent)
            }
            R.id.btn_add_cart -> {
//                val addCart = Intent(this@DetailFoodActivity, CartActivity::class.java)
                addToCart()
                finish()
//                startActivity(addCart)
            }
        }
    }

    private fun getPriceData(){
        listPrice.addAll(PriceData.listPriceData)

        val foodPrice: TextView = findViewById(R.id.txt_food_price)
        foodPrice.setText(listPrice[getPos()].toString() + "$")
    }

    private fun getImageData(){
        listImg.addAll(ImageData.listImgData)

        val foodImage: ImageView = findViewById(R.id.img_food_detail)
        Glide.with(this)
            .load(this.listImg[getPos()])
            .into(foodImage)

    }

    private fun addToCart(){
        listPrice.addAll(PriceData.listPriceData)
        listImg.addAll(ImageData.listImgData)

        val context = this
        if( getIntent().hasExtra("name") ) {

            this.name = getIntent().getStringExtra("name").toString()

            var cartFood = CartFood(
                this.name,
                etv_order.text.toString().toInt(),
                listPrice[getPos()],
                listImg[getPos()]
            )
            var db = DataBaseHeader(context)
            db.insertData(cartFood)

//            if (etv_order.text.toString().length > 0){
//                var cartFood = CartFood(
//                    this.name,
//                    etv_order.text.toString().toInt(),
//                    listPrice[getPos()],
//                    listImg[getPos()]
//                )
//                var db = DataBaseHeader(context)
//                db.insertData(cartFood)
//            }else{
//                Toast.makeText(context, "Please Fill", Toast.LENGTH_SHORT).show()
//            }

        }
    }


    private fun getPos(): Int{
        if( getIntent().hasExtra("name") ){
            this.pos = getIntent().getIntExtra("pos", -1)
        }
        return pos
    }

    private fun showDetailFood() {

        if( getIntent().hasExtra("name") ) {
            var txt_foodName: TextView = findViewById(R.id.txt_food_name)
            var txt_foodDesc: TextView = findViewById(R.id.txt_food_desc)

            this.name = getIntent().getStringExtra("name").toString()
            this.ingredient = getIntent().getStringExtra("ingredient").toString()

            txt_foodName.setText(this.name)
            txt_foodDesc.setText(this.ingredient)

        }
    }
}
