package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.masterandroid.BWareMobileApp.*
import com.masterandroid.BWareMobileApp.onboarding.CostumBottomNavBar
import com.masterandroid.BWareMobileApp.onboarding.NewsPage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragment
    private lateinit var saved_items: TextView
    private lateinit var recyclerView: RecyclerView
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

        val getStartedBtn = view.findViewById<View>(R.id.get_started_button)
        getStartedBtn.setOnClickListener{
            navigateToOtherActivity(2)
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