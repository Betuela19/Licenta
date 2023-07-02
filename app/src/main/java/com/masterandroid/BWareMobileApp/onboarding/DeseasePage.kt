package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.masterandroid.BWareMobileApp.GetDiseaseResponse
import com.masterandroid.BWareMobileApp.R
import okhttp3.*
import java.io.IOException

class DeseasePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desease_page)

        val extras = intent.extras
        val value: String = extras!!.getSerializable("position").toString()
        val intValue: Int = value.toInt()+1
        println(intValue)
        val url = "https://bwaremobileapi.azurewebsites.net/Disease/get?id="+intValue.toString()
        val request = Request.Builder().url(url).build()

        println(request)
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val diseaseResponse = Gson().fromJson(body, GetDiseaseResponse::class.java)
                println(diseaseResponse.name)

                runOnUiThread {
                    val textView = findViewById<TextView>(R.id.title_deseasePage)
                    textView?.text = diseaseResponse.name
                    val diseaseDescription = findViewById<TextView>(R.id.diseaseDescription)
                    diseaseDescription?.text = diseaseResponse.description
                    val diseaseCauses = findViewById<TextView>(R.id.diseaseCauses)
                    diseaseCauses?.text = diseaseResponse.causes
                    val diseaseSymptoms = findViewById<TextView>(R.id.diseaseSymptoms)
                    diseaseSymptoms?.text = diseaseResponse.symptoms
                    val diseaseDiagnostic = findViewById<TextView>(R.id.diseaseDiagnostic)
                    diseaseDiagnostic?.text = diseaseResponse.diagnostic
                    val diseaseCategory = findViewById<TextView>(R.id.diseaseCategory)

                    if(diseaseResponse.category == 1)
                    {
                        diseaseCategory?.text = "Children"
                    }
                    if(diseaseResponse.category == 2)
                    {
                        diseaseCategory?.text = "Adults"
                    }
                    if(diseaseResponse.category == 3)
                    {
                        diseaseCategory?.text = "Elderly"
                    }

                    val diseaseImportance = findViewById<TextView>(R.id.diseaseImportance)
                    diseaseImportance?.text = diseaseResponse.importance.toString() + "%"

                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })

        val backBtn = findViewById<ImageView>(R.id.BackBtnDeseasePage)

        backBtn.setOnClickListener {
            navigateToOtherActivity()
        }

        val donateBtn = findViewById<Button>(R.id.donate_btn)
        donateBtn.setOnClickListener{
            navigateToDonationsPage()
        }
    }

    private fun navigateToOtherActivity() {
        val intent = Intent(this, CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", 2) // Set the selected item to 2 (ArticlesFragment)
        startActivity(intent)
        finish()
    }

    private fun navigateToDonationsPage() {
        val intent = Intent(this, DonationsPage::class.java)
        startActivity(intent)
        finish()
    }


}