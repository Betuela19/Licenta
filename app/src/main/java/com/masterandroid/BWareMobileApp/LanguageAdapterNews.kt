package com.masterandroid.BWareMobileApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapterNews(
    private var mList: List<LanguageDataNews>,
    private val onItemClick: (LanguageDataNews) -> Unit
) : RecyclerView.Adapter<LanguageAdapterNews.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoNews: ImageView = itemView.findViewById(R.id.LogoNews)
        val newsTitle: TextView = itemView.findViewById(R.id.newsTitleHome)
        val newsDescription: TextView = itemView.findViewById(R.id.DescriptionNewsHome)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val languageData = mList[position]
                    onItemClick(languageData)
                }
            }
        }
    }

    fun setFilteredList(mList: List<LanguageDataNews>){
        this.mList = mList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.dashboard_item_vertical_1,
            parent,
            false
        )
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val languageData = mList[position]
        holder.logoNews.setImageResource(languageData.logo)
        holder.newsTitle.text = languageData.title
        holder.newsDescription.text = languageData.description
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
