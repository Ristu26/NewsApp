package com.ristu.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.ristu.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var data: ArrayList<Article?>? = arrayListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var name: TextView? = null
        private var tagLine: TextView? = null
        private var description: TextView? = null
        private var image: ImageView? = null

        init {
            name = view.findViewById(R.id.name)
            tagLine = view.findViewById(R.id.tag_line)
            description = view.findViewById(R.id.description)
            image = view.findViewById(R.id.image)
        }

        fun bind(news: Article?) {
            if (news != null) {
                name?.text = news.author ?: ""
                tagLine?.text = news.content ?: ""
                description?.text = news.description ?: ""

                if (!news.urlToImage.isNullOrBlank()) {
                    Glide
                        .with(image!!.rootView)
                        .load(news.urlToImage)
                        .placeholder(R.drawable.placeholder)
                        .into(image!!)
                } else {
                    image?.setImageResource(R.drawable.placeholder)
                }
            } else {
                name?.text = ""
                tagLine?.text = ""
                description?.text = ""
                image?.setImageResource(R.drawable.placeholder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_file, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data == null) {
            0
        } else {
            data!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(data: ArrayList<Article?>?) {
        this.data?.clear()
        this.data = data
        notifyDataSetChanged()
    }
}
