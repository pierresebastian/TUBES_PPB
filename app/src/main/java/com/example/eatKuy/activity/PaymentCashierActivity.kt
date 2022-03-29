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
import com.example.eatKuy.fragment.SideNavFragment

class PaymentCashierActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_cashier)


        val barcodeScan: ImageView = findViewById(R.id.barcode)
        barcodeScan.setOnClickListener(this)

        val back: ImageView = findViewById(R.id.back_img)
        back.setOnClickListener(this)
    }

    override fun onClick(v : View) {
        when(v.id) {
            R.id.barcode -> {
                val payIntent = Intent(this@PaymentCashierActivity, SuccessActivity::class.java)
                startActivity(payIntent)
            }
            R.id.back_img -> {
                val mFragmentManager = supportFragmentManager
                val mPaymentCashFragment = BillingFragment()

                val rootView: ViewGroup = findViewById(R.id.pay_cash_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(R.id.pay_cash_layout, mPaymentCashFragment,
                        BillingFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
