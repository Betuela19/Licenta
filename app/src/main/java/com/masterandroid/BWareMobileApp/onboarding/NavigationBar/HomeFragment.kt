package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import LanguageAdapterOrgans
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.masterandroid.BWareMobileApp.*
import com.masterandroid.BWareMobileApp.onboarding.CostumBottomNavBar
import com.masterandroid.BWareMobileApp.onboarding.DeseasePage
import com.masterandroid.BWareMobileApp.onboarding.NewsPage
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragment
    private lateinit var saved_items: TextView
    private lateinit var get_started: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private var mList2 = ArrayList<GetOrganTitle>()
    private lateinit var adapter2: LanguageAdapterOrgans
    private var mList = ArrayList<LanguageDataNews>()
    private lateinit var adapter: LanguageAdapterNews
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView2 = view.findViewById(R.id.horizontalRecyclerView)
        recyclerView2.setOnClickListener{
            navigateToOtherActivity(2)
        }

        var itemList = mutableListOf<GetOrganTitle>(

        // Add more items as needed
        )

        var tempList = ArrayList<GetOrganTitle>()
        val url = "https://bwaremobileapi.azurewebsites.net/Organ/get/all"
        val request = Request.Builder().url(url).build()

        println(request)
        val client2 = OkHttpClient()

        client2.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                // println(body)

                val listType = object : TypeToken<List<GetOrganTitle>>() {}.type
                val diseaseResponse: List<GetOrganTitle> = Gson().fromJson(body, listType)

                for (disease in diseaseResponse) {
                    println(disease.name)
                    tempList.add(GetOrganTitle(disease.id, disease.name))
                }

                activity?.runOnUiThread {
                    itemList.addAll(tempList)
                    adapter2.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })

       // itemList.addAll(tempList)

        adapter2 = LanguageAdapterOrgans(itemList) { selectedLanguage ->
            val position = itemList.indexOf(selectedLanguage)
            println(selectedLanguage.name)
            val intent = Intent(requireContext(), CostumBottomNavBar::class.java)
            intent.putExtra("selectedItem", 2) // Set the selected item to 3 (FavouritesFragment)
            intent.putExtra("id", position)
            intent.putExtra("organName", selectedLanguage.name)
            startActivity(intent)
            requireActivity().finish()
        }


        recyclerView2.adapter = adapter2

        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        recyclerView = view.findViewById(R.id.recyclerView_HomePage)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addDataToList()

        adapter = LanguageAdapterNews(mList) { selectedLanguage ->
            // Handle item click event here
            val intent = Intent(requireContext(), NewsPage::class.java)
            startActivity(intent)
        }

        recyclerView.adapter = adapter


        val runnable = Runnable {
            // Perform the network operation here
            val result = performNetworkOperation()

            // Update the UI on the main thread
            activity?.runOnUiThread {
                updateUI(result)
            }
        }

        // Start the thread to perform the network operation
        Thread(runnable).start()

        saved_items = view.findViewById(R.id.Saved)
        saved_items.setOnClickListener{
            navigateToOtherActivity(3)
        }

        return view
    }

    private fun addDataToList() {
        mList.add(LanguageDataNews("Influenced by light, biological rhythms say a lot about health",
            "Life patterns help humans and other animals stay in sync with nature and in good form.",
            R.drawable.ic_article))

        mList.add(LanguageDataNews("Brain disorders trigger search for new clues and cures",
            "Scientists are refining their understanding of neural networks and sharpening diagnoses. ",
            R.drawable.ic_article))

        mList.add(LanguageDataNews("The race to make hospitals cybersecure",
            "As medical centres increasingly come under attack from hackers, Europe is bolstering protection.",
            R.drawable.ic_article))
    }

    private fun performNetworkOperation(): String {
        // Your network operation code goes here
        val url = URL("https://app-form-recognizer-prod-01.azurewebsites.net/api/azure/formrecognizer/kotlin")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val inputStream = connection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        inputStream.close()
        connection.disconnect()
        return stringBuilder.toString()
    }

    private fun updateUI(result: String) {
        // Update the UI with the network result

        val textView = view?.findViewById<TextView>(R.id.Articles)
        val test = Gson().fromJson(result , TestModel::class.java)
        textView?.text = test.test
        println("test : " + test.test)
        //println("json" + json)
    }

    private fun navigateToOtherActivity(value : Int) {
        val intent = Intent(requireContext(), CostumBottomNavBar::class.java)
        intent.putExtra("selectedItem", value) // Set the selected item to 3 (FavouritesFragment)
        startActivity(intent)
        requireActivity().finish()
    }

}