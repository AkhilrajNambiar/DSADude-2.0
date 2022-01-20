package com.example.dsadude.data_structures.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.data_structures.models.DataStructureItem

class DataStructuresAdapter(
    private val items: List<DataStructureItem>
): RecyclerView.Adapter<DataStructuresAdapter.DataStructureViewHolder>() {

    inner class DataStructureViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        private val dsImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val dsName = itemView.findViewById<TextView>(R.id.item_name)

        fun bind(item: DataStructureItem) {
            dsImage.setImageResource(item.image)
            dsName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataStructureViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DataStructureViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DataStructureViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}