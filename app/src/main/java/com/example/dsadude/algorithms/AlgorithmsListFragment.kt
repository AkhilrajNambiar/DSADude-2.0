package com.example.dsadude.algorithms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.algorithms.adapters.AlgorithmsAdapter
import com.example.dsadude.algorithms.models.AlgorithmItem


class AlgorithmsListFragment : Fragment(R.layout.fragment_algorithms_list) {

    private lateinit var itemAdapter: AlgorithmsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val items = listOf(
            AlgorithmItem("Sorting", R.drawable.sorting),
            AlgorithmItem("Searching", R.drawable.ic_search)
        )
        itemAdapter = AlgorithmsAdapter(items, findNavController())
        val recyclerView = view.findViewById<RecyclerView>(R.id.algorithms_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

}