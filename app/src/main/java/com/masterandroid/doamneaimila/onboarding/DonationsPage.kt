package com.masterandroid.doamneaimila.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.doamneaimila.LanguageAdapter
import com.masterandroid.doamneaimila.LanguageData
import com.masterandroid.doamneaimila.R

class DonationsPage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations_page)

        recyclerView = findViewById(R.id.recyclerview_donations)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LanguageAdapter(mList) { selectedLanguage ->
            // Handle item click event here

        }

        recyclerView.adapter = adapter
    }

    private fun addDataToList() {
        mList.add(LanguageData("Regina Maria", R.drawable.regina_maria))
        mList.add(LanguageData("Spitalul de copii Brasov", R.drawable.spitalul_copii_brasov))
        mList.add(LanguageData("Spitalul de psihiatrie Sibiu", R.drawable.spitalul_psihiatrie_sibiu))
    }
}