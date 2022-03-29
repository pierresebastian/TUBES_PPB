package com.example.eatKuy.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

import com.example.eatKuy.R
import com.example.eatKuy.activity.AboutUsActivity
import com.example.eatKuy.activity.LocationActivity
import com.example.eatKuy.activity.MainMenuActivity
import com.example.eatKuy.activity.MyAccountActivity

class SideNavFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side_nav, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backToMain: ImageView = view.findViewById(R.id.close_side_nav)
        backToMain.setOnClickListener(this)

        val goToAccount: LinearLayout = view.findViewById(R.id.account)
        goToAccount.setOnClickListener(this)

        val goToMainMenu: LinearLayout = view.findViewById(R.id.menu)
        goToMainMenu.setOnClickListener(this)

        val goToLocation: LinearLayout = view.findViewById(R.id.location)
        goToLocation.setOnClickListener(this)

        val goToAboutUs: LinearLayout = view.findViewById(R.id.about)
        goToAboutUs.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.close_side_nav -> {
                activity?.onBackPressed()
            }
            R.id.account -> {
                val intentAccount = Intent (activity, MyAccountActivity::class.java)
                activity?.startActivity(intentAccount)
            }
            R.id.menu -> {
                val intentMenu = Intent (activity, MainMenuActivity::class.java)
                activity?.startActivity(intentMenu)
            }
            R.id.location -> {
                val intentLocation = Intent (activity, LocationActivity::class.java)
                activity?.startActivity(intentLocation)
            }
            R.id.about -> {
                val intentAbout = Intent (activity, AboutUsActivity::class.java)
                activity?.startActivity(intentAbout)
            }
        }
    }
}
