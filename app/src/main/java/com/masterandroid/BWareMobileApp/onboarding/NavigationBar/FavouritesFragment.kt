package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.masterandroid.BWareMobileApp.R


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




        return view
    }


}