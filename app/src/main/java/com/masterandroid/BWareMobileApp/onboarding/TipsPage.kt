package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masterandroid.BWareMobileApp.R

class TipsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_page)
    }

    override fun onBackPressed() {
        navigateToOtherActivity(2)
    }

    private fun navigateToOtherActivity(value : Int) {
        val intent = Intent(this, CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", value) // Set the selected item to 3 (FavouritesFragment)
        startActivity(intent)
    }
}