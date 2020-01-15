package com.example.testfls.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testfls.databinding.FragmentNewsitemBinding
import com.example.testfls.model.NewsItem

class NewsItemRecyclerViewAdapter(private val listener: NewsItemRecyclerViewAdapterCallback) : RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder>() {

    private var newsList: List<NewsItem> = listOf()

    fun setNewsItems(news: List<NewsItem>) {
        newsList = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentNewsitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(newsList[position])


    override fun getItemCount(): Int = newsList.size


    inner class ViewHolder(private val binding: FragmentNewsitemBinding, private val listener: NewsItemRecyclerViewAdapterCallback) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            this.itemView.setOnClickListener(this)
        }

        fun bind(newsItem: NewsItem){
            with(binding){
                item = newsItem
                executePendingBindings()
            }
        }


        override fun onClick(v: View?) {
            listener.onItemClick(newsList[adapterPosition].title)
        }

    }

    interface NewsItemRecyclerViewAdapterCallback {
        fun onItemClick(title: String)
    }

}
