package com.masterandroid.doamneaimila.onboarding.NavigationBar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.BarChart
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.onboarding.DeseasePage
import com.masterandroid.doamneaimila.onboarding.Results


class HomeFragment : Fragment() {

    private lateinit var homebtn : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        homebtn = view.findViewById(R.id.homebtn)

        homebtn.setOnClickListener {
            openActivity()
        }


        return view
    }

    private fun openActivity() {
        val intent = Intent(activity, DeseasePage::class.java)
        startActivity(intent)
    }


}