package com.example.eatKuy.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.eatKuy.R
import com.example.eatKuy.activity.MainMenuActivity
import com.example.eatKuy.activity.MyAccountActivity
import com.example.eatKuy.activity.PaymentActivity
import com.example.eatKuy.activity.PaymentCashierActivity

class BillingFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_billing, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backToMain: ImageView = view.findViewById(R.id.back_img)
        backToMain.setOnClickListener(this)

        val toPayment: Button = view.findViewById(R.id.btn_payment)
        toPayment.setOnClickListener(this)

        val toPaymentCashier: TextView = view.findViewById(R.id.txt_pay_cash)
        toPaymentCashier.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_img -> {
                val intentBackToMain = Intent (activity, MainMenuActivity::class.java)
                activity?.startActivity(intentBackToMain)
            }
            R.id.btn_payment -> {
                val intentPay = Intent (activity, PaymentActivity::class.java)
                activity?.startActivity(intentPay)
            }
            R.id.txt_pay_cash -> {
                val intentPayCash = Intent (activity, PaymentCashierActivity::class.java)
                activity?.startActivity(intentPayCash)
            }
        }
    }

}
