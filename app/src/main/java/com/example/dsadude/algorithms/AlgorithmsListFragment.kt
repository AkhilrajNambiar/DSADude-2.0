package com.example.dsadude.algorithms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsadude.R
import com.example.dsadude.algorithms.adapters.AlgorithmsAdapter
import com.example.dsadude.algorithms.models.AlgorithmItem


class AlgorithmsListFragment : Fragment(R.layout.fragment_algorithms_list), AlgorithmsAdapter.AlgorithmClickListener {

    private lateinit var itemAdapter: AlgorithmsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val items = listOf(
            AlgorithmItem("Sorting", R.drawable.sorting),
            AlgorithmItem("Searching", R.drawable.ic_search)
        )
        itemAdapter = AlgorithmsAdapter(items, findNavController(), this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.algorithms_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

    override fun itemClick(position: Int) {
        when(position) {
            0 -> {
                val action = AlgorithmsListFragmentDirections.actionAlgorithmsListFragmentToSortingListFragment()
                findNavController().navigate(action)
            }
            1 -> {
                val action = AlgorithmsListFragmentDirections.actionAlgorithmsListFragmentToSearchingListFragment()
                findNavController().navigate(action)
            }
            else -> {
                Toast.makeText(requireContext(), "Not yet implemented!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}