package com.masterandroid.BWareMobileApp.onboarding.NavigationBar

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.masterandroid.BWareMobileApp.*
import com.masterandroid.BWareMobileApp.onboarding.CostumBottomNavBar
import com.masterandroid.BWareMobileApp.onboarding.DeseasePage
import com.masterandroid.BWareMobileApp.onboarding.Results
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class ArticlesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var searchView: ImageButton
    private lateinit var textView: TextView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter
    private lateinit var adapter2: LanguageAdapterOrgansArticle
    private var mList2 = java.util.ArrayList<GetOrganTitle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_article, container, false)

        //get disease for filter
        textView = view.findViewById<TextView>(R.id.deseaseName)
        //

        val extras = requireActivity().intent.extras

        if(extras != null)
        {
            val value: String = extras.getSerializable("id").toString()

            if(value != null){
                val intValue: Int = value.toInt()
                val organName: String = extras.getSerializable("organName").toString()
                println(intValue)
                val textView = view.findViewById<TextView>(R.id.deseaseName)
                val textView2 = view.findViewById<Button>(R.id.organNameArticles)
                textView2?.text = organName
                textView?.text = organName
            }
        }

        recyclerView = view.findViewById(R.id.recyclerViewbeti)
        searchView = view.findViewById(R.id.searchBtn)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addDataToList()


        adapter = LanguageAdapter(mList) { selectedLanguage ->
            val position = mList.indexOf(selectedLanguage)
            println(selectedLanguage.title)
            val intent = Intent(requireContext(), DeseasePage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        var itemList = mutableListOf<GetOrganTitle>(

        )

        var tempList = java.util.ArrayList<GetOrganTitle>()
        val url = "https://bwaremobileapi.azurewebsites.net/Organ/get/all"
        val request = Request.Builder().url(url).build()

        println(request)
        val client2 = OkHttpClient()

        client2.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                // println(body)

                val listType = object : TypeToken<List<GetOrganTitle>>() {}.type
                val diseaseResponse: List<GetOrganTitle> = Gson().fromJson(body, listType)

                for (disease in diseaseResponse) {
                    println(disease.name)
                    tempList.add(GetOrganTitle(disease.id, disease.name))
                }

                activity?.runOnUiThread {
                    itemList.addAll(tempList)
                    adapter2.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })

        recyclerView2 = view.findViewById(R.id.horizontalRecyclerViewArticles)

        recyclerView2.setHasFixedSize(true)


        adapter2 = LanguageAdapterOrgansArticle(itemList) { selectedLanguage ->
            val position = itemList.indexOf(selectedLanguage)
            println(selectedLanguage.name)
            val intent = Intent(requireContext(), CostumBottomNavBar::class.java)
            intent.putExtra("selectedItem", 2) // Set the selected item to 3 (FavouritesFragment)
            intent.putExtra("id", position)
            intent.putExtra("organName", selectedLanguage.name)
            startActivity(intent)
            requireActivity().finish()
        }

        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        searchView.setOnClickListener{
            navigateToOtherActivity()
        }

        val btn_adults = view.findViewById<Button>(R.id.btnOption1)
        val btn_children = view.findViewById<Button>(R.id.btnOption2)
        val btn_eldery = view.findViewById<Button>(R.id.btnOption3)


        if(btn_adults.isSelected == false && btn_children.isSelected == false && btn_eldery.isSelected == false)
        {
            mList.clear()

            addDataToList()
        }

        btn_adults.setOnClickListener {
            btn_adults.isSelected = !btn_adults.isSelected
            btn_children.isSelected = false
            btn_eldery.isSelected = false


            mList.clear()

            if(btn_adults.isSelected == false && btn_children.isSelected == false && btn_eldery.isSelected == false)
            {
                mList.clear()

                addDataToList()
            }

            val url = "https://bwaremobileapi.azurewebsites.net/Disease/get/organ/category?category=2"
            val request = Request.Builder().url(url).build()

            println(request)
            val client = OkHttpClient()

           client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    // println(body)

                    val listType = object : TypeToken<List<GetDiseaseResponse>>() {}.type
                    val diseaseResponse: List<GetDiseaseResponse> = Gson().fromJson(body, listType)

                    val tempList = ArrayList<LanguageData>()
                    for (disease in diseaseResponse){
                        println(disease.name)
                        tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                    }

                    activity?.runOnUiThread {
                        mList.addAll(tempList)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                    e?.printStackTrace()
                }
            })

        }

        btn_children.setOnClickListener {
            btn_children.isSelected = !btn_children.isSelected
            btn_adults.isSelected = false
            btn_eldery.isSelected = false

            mList.clear()

            if(btn_adults.isSelected == false && btn_children.isSelected == false && btn_eldery.isSelected == false)
            {
                mList.clear()

                addDataToList()
            }

            val url = "https://bwaremobileapi.azurewebsites.net/Disease/get/organ/category?category=1"
            val request = Request.Builder().url(url).build()

            println(request)
            val client = OkHttpClient()

            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    // println(body)

                    val listType = object : TypeToken<List<GetDiseaseResponse>>() {}.type
                    val diseaseResponse: List<GetDiseaseResponse> = Gson().fromJson(body, listType)

                    val tempList = ArrayList<LanguageData>()
                    for (disease in diseaseResponse){
                        println(disease.name)
                        tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                    }

                    activity?.runOnUiThread {
                        mList.addAll(tempList)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                    e?.printStackTrace()
                }
            })
        }

        btn_eldery.setOnClickListener {
            btn_eldery.isSelected = !btn_children.isSelected
            btn_adults.isSelected = false
            btn_children.isSelected = false

            mList.clear()

            if(btn_adults.isSelected == false && btn_children.isSelected == false && btn_eldery.isSelected == false)
            {
                mList.clear()

                addDataToList()
            }

            val url = "https://bwaremobileapi.azurewebsites.net/Disease/get/organ/category?category=3"
            val request = Request.Builder().url(url).build()

            println(request)
            val client = OkHttpClient()

            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    // println(body)

                    val listType = object : TypeToken<List<GetDiseaseResponse>>() {}.type
                    val diseaseResponse: List<GetDiseaseResponse> = Gson().fromJson(body, listType)

                    val tempList = ArrayList<LanguageData>()
                    for (disease in diseaseResponse){
                        println(disease.name)
                        tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                    }

                    activity?.runOnUiThread {
                        mList.addAll(tempList)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                    e?.printStackTrace()
                }
            })
        }


        return view
    }

private fun addDataToList() {

        val url = "https://bwaremobileapi.azurewebsites.net/Disease/get/all"
        val request = Request.Builder().url(url).build()

        println(request)
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
               // println(body)

                val listType = object : TypeToken<List<GetDiseaseResponse>>() {}.type
                val diseaseResponse: List<GetDiseaseResponse> = Gson().fromJson(body, listType)

                val tempList = ArrayList<LanguageData>()
                for (disease in diseaseResponse){
                    //println(disease.name)

                    if(disease.organ.equals(textView.text))
                    {
                        tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                    }

                    if(textView.text.equals("Select a disease"))
                    {
                        tempList.add(LanguageData(disease.name, R.drawable.ic_article))
                    }
                }

                activity?.runOnUiThread {
                    mList.addAll(tempList)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })
    }



    private fun navigateToOtherActivity() {
        val intent = Intent(requireContext(), Results::class.java)
        startActivity(intent)
        requireActivity().finish()
    }


}

