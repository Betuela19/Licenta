package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.masterandroid.BWareMobileApp.ArticleListResponse
import com.masterandroid.BWareMobileApp.R
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class FavouritesFragment : Fragment() {

    private lateinit var binding: FavouritesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_search, container, false)

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

        return view
    }

    private fun performNetworkOperation(): String {
        // Your network operation code goes here
        val url = URL("https://bwaremobileapi.azurewebsites.net/ArticleList/get/recently/viewed?userId="+1)
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

        val textView = view?.findViewById<TextView>(R.id.recentlyViewed)
        val textView2 = view?.findViewById<TextView>(R.id.addedToday)
        val test = Gson().fromJson(result , ArticleListResponse::class.java)
        textView?.text = test.recentlyViewed.toString() + " articles"
        textView2?.text = test.addedToday.toString() + " articles"
        println("test : " + test.recentlyViewed)
        println("test : " + test.addedToday)
        //println("json" + json)
    }

}
