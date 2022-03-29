package com.example.eatKuy.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.eatKuy.R
import com.example.eatKuy.adapter.ListFoodAdapter
import com.example.eatKuy.data.ImageData
import com.example.eatKuy.fragment.BillingFragment
import com.example.eatKuy.fragment.SideNavFragment
import com.example.eatKuy.interface_api.FoodApiInterface
import com.example.eatKuy.model.Food
import com.example.eatKuy.model.FoodList
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenuActivity : AppCompatActivity(), View.OnClickListener {

    private var listImg: ArrayList<String> = arrayListOf()
    private lateinit var rvFood: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val toolbar: Toolbar = findViewById(R.id.app_toolbar_main)
        setSupportActionBar(toolbar)

        val displayCart: ImageView = findViewById(R.id.cart)
        displayCart.setOnClickListener(this)

        val sideNav: ImageView = findViewById(R.id.side_nav)
        sideNav.setOnClickListener(this)

        val orderBill: TextView = findViewById(R.id.txt_order)
        orderBill.setOnClickListener(this)

        progressBar.visibility = View.VISIBLE
        rvFood = findViewById(R.id.rv_food)

        getImageData()
        FoodApiInterface().getFoods().enqueue(object: Callback<FoodList> {
            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                val foods = response.body()
                foods?.let {
                    showFoodList(it)
                    progressBar.visibility = View.INVISIBLE
                }
            }

        })

    }

    private fun showFoodList(foods: FoodList) {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(foods)
        rvFood.adapter = listFoodAdapter
    }
    private fun getImageData(){
        listImg.addAll(ImageData.listImgData)

        val foodImage: ImageView = findViewById(R.id.img_main_menu)
        Glide.with(this)
            .load(this.listImg[0])
            .into(foodImage)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cart -> {
                val signInIntent = Intent(this@MainMenuActivity, CartActivity::class.java)
                startActivity(signInIntent)
            }
            R.id.side_nav -> {
                val mFragmentManager = supportFragmentManager
                val mSideNavFragment = SideNavFragment()

                val rootView: ViewGroup = findViewById(R.id.main_menu_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.main_menu_layout, mSideNavFragment,
                        SideNavFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.txt_order -> {
                val mFragmentManager = supportFragmentManager
                val mOrderBillFragment = BillingFragment()

                val rootView: ViewGroup = findViewById(R.id.main_menu_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.main_menu_layout, mOrderBillFragment,
                        BillingFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }

        }
    }
}
