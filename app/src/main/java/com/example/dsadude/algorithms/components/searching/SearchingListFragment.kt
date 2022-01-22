package com.example.dsadude.algorithms.components.searching

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
import com.example.dsadude.algorithms.components.searching.adapters.SearchingAdapter
import com.example.dsadude.algorithms.components.searching.models.SearchingItem

class SearchingListFragment : Fragment(R.layout.fragment_searching_list), SearchingAdapter.SearchingListener {

    private lateinit var itemAdapter: SearchingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val items = listOf(
            SearchingItem("Linear", R.drawable.linear_search)
        )
        itemAdapter = SearchingAdapter(items, this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.searching_algos_rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

    override fun onItemClick(position: Int) {
        when(position) {
            0 -> {
                val action = SearchingListFragmentDirections.actionSearchingListFragmentToLinearSearchFragment()
                findNavController().navigate(action)
            }
            else -> {
                Toast.makeText(requireContext(), "Not implemented, yet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}