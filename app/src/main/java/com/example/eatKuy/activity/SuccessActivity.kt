package com.example.eatKuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.eatKuy.R

class SuccessActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val closeSuccess: ImageView = findViewById(R.id.close_success)
        closeSuccess.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.close_success -> {
                val intentCloseSuccess = Intent(this@SuccessActivity, MainMenuActivity::class.java)
                startActivity(intentCloseSuccess)
            }
        }
    }
}
