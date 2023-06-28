package com.masterandroid.doamneaimila.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.masterandroid.doamneaimila.GetDiseaseResponse
import com.masterandroid.doamneaimila.LanguageAdapter
import com.masterandroid.doamneaimila.LanguageData
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.R.id.searchViewResult
import com.masterandroid.doamneaimila.onboarding.screens.FilterPage
import okhttp3.*
import java.io.IOException
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
        adapter = LanguageAdapter(mList) { selectedLanguage ->
            // Handle item click event here
            val intent = Intent(this, DeseasePage::class.java)
            startActivity(intent)
        }

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

        val filterIcon = findViewById<ImageView>(R.id.filterIcon)

        filterIcon.setOnClickListener {
            navigateToFilter()
        }
    }

    private fun navigateToFilter() {
        val intent = Intent(this, FilterPage::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToOtherActivity() {
        val intent = Intent(this, CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", 2) // Set the selected item to 2 (ArticlesFragment)
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
        val url = "https://bwaremobileapi.azurewebsites.net/Disease/get/all"
        val request = Request.Builder().url(url).build()

        println(request)
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                // println(body)

                val listType = object : TypeToken<List<GetDiseaseResponse>>() {}.type
                val diseaseResponse: List<GetDiseaseResponse> = Gson().fromJson(body, listType)

                val tempList = java.util.ArrayList<LanguageData>()
                for (disease in diseaseResponse){
                    println(disease.name)
                    tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                }

                this@Results.runOnUiThread {
                    mList.addAll(tempList)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })
    }

}