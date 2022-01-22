package com.example.dsadude.algorithms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.algorithms.AlgorithmsListFragmentDirections
import com.example.dsadude.algorithms.models.AlgorithmItem
import com.example.dsadude.data_structures.adapters.DataStructuresAdapter
import com.example.dsadude.data_structures.models.DataStructureItem
import com.google.android.material.card.MaterialCardView

class AlgorithmsAdapter(
    private val items: List<AlgorithmItem>,
    private val navController: NavController
): RecyclerView.Adapter<AlgorithmsAdapter.AlgorithmViewHolder>() {

    inner class AlgorithmViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        private val dsImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val dsName = itemView.findViewById<TextView>(R.id.item_name)
        private val itemCard = itemView.findViewById<MaterialCardView>(R.id.item_card)

        fun bind(item: AlgorithmItem) {
            dsImage.setImageResource(item.image)
            dsName.text = item.name

            itemCard.setOnClickListener {
                if (item.name == "Sorting") {
                    val action = AlgorithmsListFragmentDirections.actionAlgorithmsListFragmentToSortingListFragment()
                    navController.navigate(action)
                }
                else if (item.name == "Searching") {
                    val action = AlgorithmsListFragmentDirections.actionAlgorithmsListFragmentToSearchingListFragment()
                    navController.navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgorithmViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return AlgorithmViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AlgorithmViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}