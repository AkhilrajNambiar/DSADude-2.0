package com.example.dsadude.algorithms.components.sorting

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
import com.example.dsadude.algorithms.components.sorting.adapters.SortingAdapter
import com.example.dsadude.algorithms.components.sorting.models.SortingItem


class SortingListFragment : Fragment(R.layout.fragment_sorting_list), SortingAdapter.SortingListener {

    private lateinit var itemAdapter: SortingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val items = listOf<SortingItem>(
            SortingItem("Insertion", R.drawable.insertion_sort),
            SortingItem("Bubble", R.drawable.bubble_sort),
            SortingItem("Merge", R.drawable.merge_sort),
            SortingItem("Quick", R.drawable.quick_sort),
            SortingItem("Selection", R.drawable.selection_sort)
        )
        itemAdapter = SortingAdapter(items, this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.sorting_algos_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

    override fun onItemClick(position: Int) {
        when(position) {
            0 -> { // insertion sort
                val action = SortingListFragmentDirections.actionSortingListFragmentToInsertionSortFragment()
                findNavController().navigate(action)
            }
            1 -> { // bubble_sort
                val action = SortingListFragmentDirections.actionSortingListFragmentToBubbleSortFragment()
                findNavController().navigate(action)
            }
            2 -> {
                val action = SortingListFragmentDirections.actionSortingListFragmentToMergeSortFragment()
                findNavController().navigate(action)
            }
            3 -> {
                val action = SortingListFragmentDirections.actionSortingListFragmentToQuickSortFragment()
                findNavController().navigate(action)
            }
            4 -> {
                val action = SortingListFragmentDirections.actionSortingListFragmentToSelectionSortFragment()
                findNavController().navigate(action)
            }
            else -> {
                Toast.makeText(requireContext(), "Not implemented, yet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}