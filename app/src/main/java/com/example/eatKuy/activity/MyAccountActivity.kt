package com.example.eatKuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.eatKuy.R
import com.example.eatKuy.fragment.SideNavFragment

class MyAccountActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        val sideNav: ImageView = findViewById(R.id.side_nav)
        sideNav.setOnClickListener(this)

        val toHistory: Button = findViewById(R.id.btn_history)
        toHistory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.side_nav -> {
                val mFragmentManager = supportFragmentManager
                val mSideNavFragment = SideNavFragment()

                val rootView: ViewGroup = findViewById(R.id.account_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.account_layout, mSideNavFragment,
                        SideNavFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.btn_history -> {
                val historyIntent = Intent(this@MyAccountActivity, HistoryActivity::class.java)
                startActivity(historyIntent)
            }
        }
    }
}
