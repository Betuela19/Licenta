package com.masterandroid.BWareMobileApp.onboarding.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.masterandroid.BWareMobileApp.R
import com.masterandroid.BWareMobileApp.onboarding.CostumBottomNavBar
import com.masterandroid.BWareMobileApp.onboarding.NavigationBar.ArticlesFragment
import com.masterandroid.BWareMobileApp.onboarding.Results

class FilterPage : AppCompatActivity() {
    private lateinit var dialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_page)

        showBottomSheet()
    }

    private fun showBottomSheet(){
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)

        // Find the exit button in the bottom sheet layout
        val exitButton = dialogView.findViewById<ImageView>(R.id.cancelButton)

        // Set click listener for the exit button
        exitButton.setOnClickListener {
            // Close the bottom sheet
            dialog.dismiss()
            //navigateToFragment()
            navigateToResults()
        }

        // Adjust dialog behavior to expand to full height
        val bottomSheetBehavior = BottomSheetBehavior.from(dialogView.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.peekHeight = 0

        dialog.show()
    }

    private fun navigateToOtherActivity() {
        val intent = Intent(this, CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", 2) // Set the selected item to 2 (ArticlesFragment)
        startActivity(intent)
        finish()
    }
    private fun navigateToResults() {
        val intent = Intent(this, Results::class.java)
        startActivity(intent)
        finish()
    }



    private fun navigateToFragment() {
        // Create an instance of your fragment
        val fragment = ArticlesFragment()

        // Get the FragmentManager
        val fragmentManager = this.supportFragmentManager

        // Begin the fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // Replace the current fragment with the new fragment
        transaction.replace(android.R.id.content, fragment)  // Use android.R.id.content to replace the entire activity content

        // Add the transaction to the back stack (optional)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }
}