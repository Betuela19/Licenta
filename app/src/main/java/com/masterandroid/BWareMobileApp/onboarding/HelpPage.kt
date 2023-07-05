package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.BWareMobileApp.LanguageAdapterHelp
import com.masterandroid.BWareMobileApp.LanguageDataHelp
import com.masterandroid.BWareMobileApp.R
import java.util.*
import kotlin.collections.ArrayList

class HelpPage : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<LanguageDataHelp>()
    private lateinit var adapter: LanguageAdapterHelp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_page)

        recyclerView = findViewById(R.id.recycler_help)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LanguageAdapterHelp(mList)
        recyclerView.adapter = adapter

    }
    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageDataHelp>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(
            LanguageDataHelp(
                "About BWare",
                R.drawable.ic_help,
                "The main purpose of the application is to provide users with a centralized and reliable source to access relevant and up-to-date information in the field of health."
            )
        )
        mList.add(
            LanguageDataHelp(
                "How it works?",
                R.drawable.ic_help,
                "1.To visualise the most recent news regardin health select a news from the home page" +
                        "2.To see the page for an article select the desired organ from the home page" +
                        "3.Search a disease by clicking on the search icon in Articles page" +
                        "4.To add an article to favourites, click on the fevourite icon on the presentation page of a desease" +
                        "5.To see details about the account click on the corresponding icon in the navbar"
            )
        )
        mList.add(
            LanguageDataHelp(
                "How to make a donation?",
                R.drawable.ic_help,
                "To make a donation click on the donate button in the presentation page of a disease, then click on the institution you want to make the donation.You will be redirected to the website of the selected option"
            )
        )
        mList.add(
            LanguageDataHelp(
                "How to provide feedback?",
                R.drawable.ic_help,
                "To provide a feedback navigate to the account page, then click on the Give a feedback button.A form will be displayed."
            )
        )
        mList.add(
            LanguageDataHelp(
                "My articles",
                R.drawable.ic_help,
                "All the saved articles will be shown in the corresponding page"
            )
        )
        mList.add(
            LanguageDataHelp(
                "Notifications",
                R.drawable.ic_help,
                "Currently notifications are not available"
            )
        )
    }

    override fun onBackPressed() {
        navigateToOtherActivity(5)
    }

    private fun navigateToOtherActivity(value : Int) {
        val intent = Intent(this, CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", value) // Set the selected item to 3 (FavouritesFragment)
        startActivity(intent)
    }
}