package com.example.dsadude.data_structures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.data_structures.adapters.DataStructuresAdapter
import com.example.dsadude.data_structures.models.DataStructureItem

class DataStructuresListFragment : Fragment(R.layout.fragment_data_structures_list) {

    private lateinit var itemAdapter: DataStructuresAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_structures_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dsList = listOf(
            DataStructureItem("Array", R.drawable.array),
            DataStructureItem("Linked List", R.drawable.linkedlist),
            DataStructureItem("Stack", R.drawable.stack),
            DataStructureItem("Queue", R.drawable.queue),
            DataStructureItem("Tree", R.drawable.tree),
            DataStructureItem("Graph", R.drawable.graph)
        )
        itemAdapter = DataStructuresAdapter(dsList)
        val recyclerView = view.findViewById<RecyclerView>(R.id.data_strutures_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

}