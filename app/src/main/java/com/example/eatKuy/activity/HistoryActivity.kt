package com.example.eatKuy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eatKuy.R

class HistoryActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val backToAccount: ImageView = findViewById(R.id.back_img)
        backToAccount.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.back_img -> {
                val myAccountIntent = Intent(this@HistoryActivity, MyAccountActivity::class.java)
                startActivity(myAccountIntent)
            }
        }
    }
}