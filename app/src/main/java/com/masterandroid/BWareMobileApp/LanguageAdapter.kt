package com.masterandroid.BWareMobileApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import java.io.IOException

class LanguageAdapter(
    private var mList: List<LanguageData>,
    private val onItemClick: (selectedLanguage: LanguageData) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val languageData = mList[position]
                    onItemClick(languageData)
                }
                updateArticleList()
            }
        }
    }

    fun setFilteredList(mList: List<LanguageData>){
        this.mList = mList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.each_item_search,
            parent,
            false
        )
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val languageData = mList[position]
        holder.logo.setImageResource(languageData.logo)
        holder.titleTv.text = languageData.title
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    private fun updateArticleList() {
        val url = "https://bwaremobileapi.azurewebsites.net/ArticleList/recently/viewed"

        val queryUrl = HttpUrl.parse(url)?.newBuilder()
            ?.addQueryParameter("userId", "1")
            ?.build()

        val request = Request.Builder()
            .url(queryUrl)
            .post(RequestBody.create(null, ""))
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                // Handle the response body as needed
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })
    }
}
