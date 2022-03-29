package com.example.eatKuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.eatKuy.R
import com.example.eatKuy.fragment.BillingFragment

class PaymentActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val mandiriPay: ImageView = findViewById(R.id.mandiri)
        mandiriPay.setOnClickListener(this)
        val briPay: ImageView = findViewById(R.id.bri)
        briPay.setOnClickListener(this)
        val bcaPay: ImageView = findViewById(R.id.bca)
        bcaPay.setOnClickListener(this)
        val goPay: ImageView = findViewById(R.id.gopay)
        goPay.setOnClickListener(this)
        val ovoPay: ImageView = findViewById(R.id.ovo)
        ovoPay.setOnClickListener(this)
        val danaPay: ImageView = findViewById(R.id.dana)
        danaPay.setOnClickListener(this)

        val back: ImageView = findViewById(R.id.back_img)
        back.setOnClickListener(this)


    }

    override fun onClick(v : View) {
        when(v.id) {
            R.id.mandiri, R.id.bri,
            R.id.bca, R.id.gopay,
            R.id.ovo, R.id.dana -> {
                val payIntent = Intent(this@PaymentActivity, SuccessActivity::class.java)
                startActivity(payIntent)
            }
            R.id.back_img -> {
                val mFragmentManager = supportFragmentManager
                val mPaymentFragment = BillingFragment()

                val rootView: ViewGroup = findViewById(R.id.pay_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(R.id.pay_layout, mPaymentFragment,
                        BillingFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
