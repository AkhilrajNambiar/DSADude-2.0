package com.example.dsadude.data_structures.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.data_structures.models.DataStructureItem
import com.google.android.material.card.MaterialCardView

class DataStructuresAdapter(
    private val items: List<DataStructureItem>,
    private var dataStructureClickListener: DataStructureClickListener
): RecyclerView.Adapter<DataStructuresAdapter.DataStructureViewHolder>() {

    inner class DataStructureViewHolder(
        itemView: View,
        private var dataStructureClickListener: DataStructureClickListener
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val dsImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val dsName = itemView.findViewById<TextView>(R.id.item_name)
        private val itemCard = itemView.findViewById<MaterialCardView>(R.id.item_card)

        fun bind(item: DataStructureItem) {
            dsImage.setImageResource(item.image)
            dsName.text = item.name
            itemCard.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            dataStructureClickListener.onClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataStructureViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DataStructureViewHolder(layout, dataStructureClickListener)
    }

    override fun onBindViewHolder(holder: DataStructureViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

interface DataStructureClickListener {
    fun onClick(position: Int)
}