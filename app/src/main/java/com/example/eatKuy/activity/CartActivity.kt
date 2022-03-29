package com.example.eatKuy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.eatKuy.R
import com.example.eatKuy.db.DataBaseHeader
import kotlinx.android.synthetic.main.activity_cart.*
import org.w3c.dom.Text

class CartActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val back: ImageView = findViewById(R.id.back_img)
        back.setOnClickListener(this)

        getFromDetail()

        val context = this
        var db = DataBaseHeader(context)

        btn_delete.setOnClickListener {
            db.deleteData()
            finish()
            startActivity(getIntent())
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_img -> {
                val backToMainIntent = Intent(this@CartActivity, MainMenuActivity::class.java)
                startActivity(backToMainIntent)
            }
        }
    }

    private fun getFromDetail(){
        var txtFoodCart: TextView = findViewById(R.id.txt_food_cart)
        var txtQtyCart: TextView = findViewById(R.id.txt_food_qty)
        var txtPriceCart: TextView = findViewById(R.id.txt_price_cart)
        var imgFood: ImageView = findViewById(R.id.img_food_cart)

        val context = this
        var db = DataBaseHeader(context)
        var data = db.readData()
        for (i in 0..data.size-1){
            txtFoodCart.setText(data.get(i).name)
            txtQtyCart.setText(data.get(i).qty.toString())
            txtPriceCart.setText(data.get(i).price.toString() + "$")
            Glide.with(this)
                .load(data.get(i).image)
                .into(imgFood)
        }

    }


}