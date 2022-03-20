package com.example.dsadude.data_structures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.data_structures.adapters.DataStructureClickListener
import com.example.dsadude.data_structures.adapters.DataStructuresAdapter
import com.example.dsadude.data_structures.models.DataStructureItem

class DataStructuresListFragment : Fragment(R.layout.fragment_data_structures_list), DataStructureClickListener {

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
        itemAdapter = DataStructuresAdapter(dsList, this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.data_strutures_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

    override fun onClick(position: Int) {
        when(position) {
            0 -> {
                val action = DataStructuresListFragmentDirections.actionDataStructuresListFragmentToArrayFragment()
                findNavController().navigate(action)
            }
            1 -> {
                val action = DataStructuresListFragmentDirections.actionDataStructuresListFragmentToLinkedListFragment()
                findNavController().navigate(action)
            }
            2 -> {
                val action = DataStructuresListFragmentDirections.actionDataStructuresListFragmentToStackFragment()
                findNavController().navigate(action)
            }
        }
    }

}