package com.techugo.sidemenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techugo.bottomnavigation.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_navigation.*
import kotlinx.android.synthetic.main.home_navigation.view.*

class MainActivity : BaseActivity(), View.OnClickListener, Fragment1.OnNotifyRelodPage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
//.........................SideMenu------------------------------------------
        imgHomeNavigation.setOnClickListener(this)
        imgCrossHomeNavigation.setOnClickListener(this)
        navigation.llScheduleTask.setOnClickListener(this)
        navigation.llTimesLogs.setOnClickListener(this)
//......................................................................................

//......................Buttom navigation............................
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        replaceFragment(R.id.homeContainer, Fragment1())
        nav_view.itemIconTintList = null
//-------------------------------------------------------------------------------------------
    }
    //........................... SideMenu---------------------------------------------------
    override fun onClick(v: View?) {
        //open Drawer
        if (v == imgHomeNavigation) {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        //close Drawer
        if (v == imgCrossHomeNavigation) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        //navigate to next Activity
        if (v == llScheduleTask) {
            //drawer_layout.closeDrawer(GravityCompat.START)
            val intent = Intent(this@MainActivity, Activity1::class.java)
            startActivity(intent)
        }

        if (v==llTimesLogs){
            val intent = Intent(this@MainActivity,Activity2::class.java)
            startActivity(intent)
        }
    }
    //.................................................................................



//........................TODO Buttom Navigation......................................

    override fun reloadPage(position: Int) {
        replaceFragment(R.id.homeContainer, Fragment1())
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is Fragment1) {
            fragment.setOnHeadlineSelectedListener(this@MainActivity)
        }
    }



    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(R.id.homeContainer, Fragment1())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_message -> {
                replaceFragment(R.id.homeContainer, Fragment2())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_worker -> {
                replaceFragment(R.id.homeContainer, Fragment3())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFragment(R.id.homeContainer, Fragment4()
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                //  homeContainer.setBackgroundColor(resources.getColor(R.color.white))
                replaceFragment(R.id.homeContainer, Fragment5())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
