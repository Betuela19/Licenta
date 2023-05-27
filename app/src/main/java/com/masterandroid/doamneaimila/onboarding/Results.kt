package com.masterandroid.doamneaimila.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.doamneaimila.LanguageAdapter
import com.masterandroid.doamneaimila.LanguageData
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.R.id.searchViewResult
import com.masterandroid.doamneaimila.onboarding.screens.FilterPage
import java.util.*
import kotlin.collections.ArrayList

class Results : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        recyclerView = findViewById(R.id.recyclerViewResult)
        searchView = findViewById(searchViewResult)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        val backButton = findViewById<ImageView>(R.id.back_btn)

        // Set click listener for the exit button
        backButton.setOnClickListener {
            navigateToOtherActivity()
        }
    }

    private fun navigateToOtherActivity() {
        val intent = Intent(this, FilterPage::class.java)

        startActivity(intent)
        finish()
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
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
        mList.add(LanguageData("Java", R.drawable.ic_person))
        mList.add(LanguageData("Kotlin", R.drawable.ic_article))
        mList.add(LanguageData("C++", R.drawable.ic_graph))
    }

}