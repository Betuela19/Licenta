package com.masterandroid.doamneaimila.onboarding.NavigationBar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.BarChart
import com.google.gson.Gson
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.TestModel
import com.masterandroid.doamneaimila.onboarding.CostumBottomNavBar
import com.masterandroid.doamneaimila.onboarding.DeseasePage
import com.masterandroid.doamneaimila.onboarding.Results
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragment
    private lateinit var saved_items: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)


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