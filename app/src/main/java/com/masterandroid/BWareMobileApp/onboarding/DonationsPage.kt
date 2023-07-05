package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.BWareMobileApp.LanguageAdapter
import com.masterandroid.BWareMobileApp.LanguageData
import com.masterandroid.BWareMobileApp.R

class DonationsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations_page)

        val reginaMaria = findViewById<TextView>(R.id.reginaMaria)
       reginaMaria.setOnClickListener {
            val url = "https://www.google.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val brasov = findViewById<TextView>(R.id.spitalulBrasov)
        brasov.setOnClickListener {
            val url = "http://www.spitalcopiibrasov.ro/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }

}