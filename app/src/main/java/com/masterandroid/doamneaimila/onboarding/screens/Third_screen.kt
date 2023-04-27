package com.masterandroid.doamneaimila.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.onboarding.LogIn
import com.masterandroid.doamneaimila.onboarding.SignUp


class Third_screen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third_screen, container, false)

       val login_button = view.findViewById<Button>(R.id.logIn_button)

        login_button.setOnClickListener{
            val intent = Intent(requireContext(),LogIn::class.java)
            startActivity(intent)
        }

        val signup_button = view.findViewById<Button>(R.id.signUp_button)

        signup_button.setOnClickListener{
            val intent = Intent(requireContext(),SignUp::class.java)
            startActivity(intent)
        }
        return view
    }


}