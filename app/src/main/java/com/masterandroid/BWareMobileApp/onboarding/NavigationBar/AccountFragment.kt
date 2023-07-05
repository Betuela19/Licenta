package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.masterandroid.BWareMobileApp.R
import com.masterandroid.BWareMobileApp.onboarding.HelpPage
import com.masterandroid.BWareMobileApp.onboarding.TipsPage


class AccountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_account, container, false)

        val help_btn = view.findViewById<TextView>(R.id.HelpBtn)
        help_btn.setOnClickListener {
            val intent = Intent(requireContext(), HelpPage::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        val feedback_btn = view.findViewById<TextView>(R.id.GiveFeedback)
        feedback_btn.setOnClickListener {
            val url = "https://forms.gle/jGgJUQg6CXGUNG8TA"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        return view
    }


}