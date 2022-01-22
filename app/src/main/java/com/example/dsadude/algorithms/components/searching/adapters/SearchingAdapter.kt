package com.example.dsadude.algorithms.components.searching.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.algorithms.components.searching.models.SearchingItem
import com.example.dsadude.algorithms.components.sorting.adapters.SortingAdapter
import com.example.dsadude.algorithms.components.sorting.models.SortingItem
import com.google.android.material.card.MaterialCardView

class SearchingAdapter(
    private val items: List<SearchingItem>,
    private var searchingClickListener: SearchingListener
): RecyclerView.Adapter<SearchingAdapter.SearchingViewHolder>(){
    inner class SearchingViewHolder(
        itemView: View,
        private var searchingClickListener: SearchingListener
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val dsImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val dsName = itemView.findViewById<TextView>(R.id.item_name)
        private val itemCard = itemView.findViewById<MaterialCardView>(R.id.item_card)

        fun bind(item: SearchingItem) {
            dsImage.setImageResource(item.image)
            dsName.text = item.name
            itemCard.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            searchingClickListener.onItemClick(adapterPosition)
        }
    }

    interface SearchingListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchingViewHolder(layout, searchingClickListener)
    }

    override fun onBindViewHolder(holder: SearchingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}