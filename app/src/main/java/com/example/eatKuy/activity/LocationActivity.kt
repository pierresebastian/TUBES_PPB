package com.example.eatKuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.eatKuy.R
import com.example.eatKuy.fragment.SideNavFragment

class LocationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val sideNav: ImageView = findViewById(R.id.side_nav)
        sideNav.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.side_nav -> {
                val mFragmentManager = supportFragmentManager
                val mSideNavFragment = SideNavFragment()

                val rootView: ViewGroup = findViewById(R.id.location_layout)
                val mFade: Fade = Fade(Fade.IN)
                TransitionManager.beginDelayedTransition(rootView, mFade)

                mFragmentManager
                    .beginTransaction()
                    .add(R.id.location_layout, mSideNavFragment,
                        SideNavFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
