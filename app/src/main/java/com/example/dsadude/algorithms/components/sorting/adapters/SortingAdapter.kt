package com.example.dsadude.algorithms.components.sorting.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.algorithms.adapters.AlgorithmsAdapter
import com.example.dsadude.algorithms.components.sorting.models.SortingItem
import com.example.dsadude.algorithms.models.AlgorithmItem
import com.google.android.material.card.MaterialCardView

class SortingAdapter(
    private val items: List<SortingItem>,
    private var sortingClickListener: SortingListener
): RecyclerView.Adapter<SortingAdapter.SortingViewHolder>() {

    inner class SortingViewHolder(
        itemView: View,
        private var sortingClickListener: SortingListener
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val dsImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val dsName = itemView.findViewById<TextView>(R.id.item_name)
        private val itemCard = itemView.findViewById<MaterialCardView>(R.id.item_card)

        fun bind(item: SortingItem) {
            dsImage.setImageResource(item.image)
            dsName.text = item.name
            itemCard.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            sortingClickListener.onItemClick(adapterPosition)
        }
    }

    interface SortingListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SortingViewHolder(layout, sortingClickListener)
    }

    override fun onBindViewHolder(holder: SortingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}