package com.example.testfls.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testfls.R
import com.example.testfls.model.NewsItem
import kotlinx.android.synthetic.main.fragment_newsitem.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsItemRecyclerViewAdapter(private val listener: NewsItemRecyclerViewAdapterCallback) : RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder>() {

    private var newsList: List<NewsItem> = listOf()

    private val millsToStringPattern = "EEE, dd LLL yyyy HH:mm:ss"

    fun setNewsItems(news: List<NewsItem>) {
        newsList = news
        notifyDataSetChanged()
    }

//    fun getNewsItems(): List<NewsItem> = newsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_newsitem, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsList[position]
        holder.mTitleView.text = item.title
        holder.mTimeView.text = dateMillsToDateStr(item.pubDate)
        holder.mAuthorView.text = item.author

    }

    override fun getItemCount(): Int = newsList.size


    private fun dateMillsToDateStr(dateMills: Long): String =
        SimpleDateFormat(millsToStringPattern, Locale.getDefault()).format(Date(dateMills))


    inner class ViewHolder(mView: View, private val listener: NewsItemRecyclerViewAdapterCallback) : RecyclerView.ViewHolder(mView), View.OnClickListener {
        init {
            this.itemView.setOnClickListener(this)
        }

        val mTitleView: TextView = mView.title
        val mTimeView: TextView = mView.time
        val mAuthorView: TextView = mView.author

        override fun onClick(v: View?) {
            listener.onItemClick(newsList[adapterPosition].title)
        }

        override fun toString(): String {
            return super.toString() + " '" + mTitleView.text + "'"
        }
    }

    interface NewsItemRecyclerViewAdapterCallback {
        fun onItemClick(title: String)
    }

}
