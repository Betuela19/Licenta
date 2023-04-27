package com.masterandroid.doamneaimila.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.onboarding.screens.Screen_One
import com.masterandroid.doamneaimila.onboarding.screens.Second_Screen
import com.masterandroid.doamneaimila.onboarding.screens.Third_screen


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            Screen_One(),
            Second_Screen(),
            Third_screen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.findViewById<ViewPager2>(R.id.viewPager).adapter = adapter

        return view
    }


}