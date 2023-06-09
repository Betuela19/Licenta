package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.etebarian.meowbottomnavigation.MeowBottomNavigation.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.masterandroid.BWareMobileApp.GetDiseaseResponse
import com.masterandroid.BWareMobileApp.R
import com.masterandroid.BWareMobileApp.onboarding.NavigationBar.*
import okhttp3.*
import java.io.IOException


class CostumBottomNavBar : AppCompatActivity() {
    var bottomNav: MeowBottomNavigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costum_bottom_nav_bar)

        //assign the variable
        bottomNav = findViewById(R.id.bottomNav)
        val meowBottomNav = bottomNav as MeowBottomNavigation
        //add menu items to bottom nav

        meowBottomNav.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        meowBottomNav.add(MeowBottomNavigation.Model(2, R.drawable.ic_article))
        meowBottomNav.add(MeowBottomNavigation.Model(3, R.drawable.ic_favourite_2))
        meowBottomNav.add(MeowBottomNavigation.Model(4, R.drawable.ic_graph))
        meowBottomNav.add(MeowBottomNavigation.Model(5, R.drawable.ic_person))

        //set count to dashboard item
        meowBottomNav.setCount(1, "")

        //set bottom nav on show listener
        meowBottomNav.setOnShowListener(ShowListener { item -> //initialize fragment
            var fragment: Fragment? = null

            if (item.id == 1) {
                fragment = HomeFragment()


            } else if (item.id == 2) {
                fragment = ArticlesFragment()
            } else if (item.id == 3) {
                fragment = FavouritesFragment()
            }else if (item.id == 4) {
                fragment = StatisticsFragment()
            }else if (item.id == 5) {
                fragment = AccountFragment()
            }

            //load fragment
            loadFragment(fragment)
        })
        //set initial selected fragment
        meowBottomNav.show(1, true)
        val selectedItem = intent.getIntExtra("selectedItem", 1) // Default to item 1 if not provided

        // Set initial selected fragment based on the selected item index
        meowBottomNav.show(selectedItem, true)
        // set menu item on click
        meowBottomNav.setOnClickMenuListener(ClickListener { item -> //display something
            //Toast.makeText(applicationContext, "You clicked" + item.id, Toast.LENGTH_SHORT).show()
        })
        //set on reselect
       meowBottomNav.setOnReselectListener(ReselectListener { item ->
            Toast.makeText(
                applicationContext,
                "You reselected" + item.id,
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun loadFragment(fragment: Fragment?) {
        //replace the fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_container, fragment!!, null)
            .commit()
    }
}