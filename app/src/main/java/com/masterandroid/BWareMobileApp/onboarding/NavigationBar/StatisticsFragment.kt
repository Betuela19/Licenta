package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.masterandroid.BWareMobileApp.R


class StatisticsFragment : Fragment() {

    private lateinit var barChart : BarChart;
    private lateinit var pieChart : PieChart;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        barChart = view.findViewById(R.id.bar_chart)
        pieChart = view.findViewById(R.id.pie_chart)


        val barEntries = ArrayList<BarEntry>()
        val pieEntries = ArrayList<PieEntry>()

        for (i in 1..9) {
            val value = (i * 10.0).toFloat()
            val barEntry = BarEntry(i.toFloat(), value)
            val pieEntry = PieEntry(i.toFloat(), value)
            barEntries.add(barEntry)
            pieEntries.add(pieEntry)
        }

        val barDataSet = BarDataSet(barEntries, "Health rate")
//Set colors
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        barDataSet.setDrawValues(false)

        val pieDataSet = PieDataSet(pieEntries, "Health rate")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()

        barChart.data = BarData(barDataSet)
        barChart.animateY(5000)
        barChart.description.isEnabled = false

        pieChart.data = PieData(pieDataSet)
        pieChart.animateXY(5000, 5000)
        pieChart.description.isEnabled = false


        return view


    }

}