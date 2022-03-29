package com.example.eatKuy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.eatKuy.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.CirclePageIndicator
import com.synnapps.carouselview.ImageListener


class GetStartedActivity : AppCompatActivity(), View.OnClickListener {

    var carouselImage = intArrayOf(
        R.drawable.getstart1,
        R.drawable.getstart3,
        R.drawable.getstart4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val btnGetStarted: Button = findViewById(R.id.btn_get_started)
        btnGetStarted.setOnClickListener(this)

        val carouselView: CarouselView = findViewById(R.id.carouselView)
        carouselView.setPageCount(carouselImage.size)
        carouselView.setImageListener(imageListener)
        val indicator: CirclePageIndicator = carouselView.findViewById(R.id.indicator)
        indicator.visibility = View.GONE
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_get_started -> {
                val signInIntent = Intent(this@GetStartedActivity, SignInActivity::class.java)
                startActivity(signInIntent)
            }
        }
    }

    private var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            imageView.setImageResource(carouselImage[position])
        }
    }
}
