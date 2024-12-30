package com.mandiri.aplikasiberita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.mandiri.aplikasiberita.R
import com.mandiri.aplikasiberita.data.model.ArticlesItemEverything
import com.mandiri.aplikasiberita.databinding.ItemAllBinding
import com.mandiri.aplikasiberita.utils.formatDate

class EverythingAdapterPaging(
    private val itemClickCallback: (ArticlesItemEverything) -> Unit
) : PagingDataAdapter<ArticlesItemEverything, RecyclerView.ViewHolder>(NewsComparator) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as NewsViewHolder<*>).bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemAllBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding, itemClickCallback)
    }

    class NewsViewHolder<T : ViewBinding>(
        private val binding: T,
        private val itemClickCallback: (ArticlesItemEverything) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItemEverything) {
            with(binding as ItemAllBinding) {
                ivAllNews.load(data.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder_image)
                    error(R.drawable.placeholder_image)
                }
                tvTitleAllNews.text = data.title
                tvAuthorAllNews.text = data.author
                tvDateAllNews.text = formatDate(data.publishedAt)
            }

            itemView.setOnClickListener { itemClickCallback(data) }
        }
    }


    object NewsComparator : DiffUtil.ItemCallback<ArticlesItemEverything>() {
        override fun areItemsTheSame(
            oldItem: ArticlesItemEverything,
            newItem: ArticlesItemEverything
        ): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: ArticlesItemEverything,
            newItem: ArticlesItemEverything
        ): Boolean =
            oldItem == newItem
    }


}