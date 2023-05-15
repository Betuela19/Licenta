package com.masterandroid.doamneaimila.onboarding.NavigationBar

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterandroid.doamneaimila.LanguageAdapter
import com.masterandroid.doamneaimila.LanguageData
import com.masterandroid.doamneaimila.R
import java.util.*


class ArticlesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_article, container, false)


        recyclerView = view.findViewById(R.id.recyclerViewbeti)
        searchView = view.findViewById(R.id.searchViewbeti)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.requestFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchView.requestFocus()
                filterList(newText)
                return false
            }

        })

        val btn_adults = view.findViewById<Button>(R.id.btnOption1)
        val btn_children = view.findViewById<Button>(R.id.btnOption2)
        val btn_eldery = view.findViewById<Button>(R.id.btnOption3)


        btn_adults.setOnClickListener {
            btn_adults.isSelected = !btn_adults.isSelected
            btn_children.isSelected = false
            btn_eldery.isSelected = false
        }

        btn_children.setOnClickListener {
            btn_children.isSelected = !btn_children.isSelected
            btn_adults.isSelected = false
            btn_eldery.isSelected = false
        }

        btn_eldery.setOnClickListener {
            btn_eldery.isSelected = !btn_children.isSelected
            btn_adults.isSelected = false
            btn_children.isSelected = false
        }

        return view
    }
    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("Java", R.drawable.ic_article))
        mList.add(LanguageData("Kotlin", R.drawable.ic_person))
        mList.add(LanguageData("C++", R.drawable.ic_home))

    }

}

