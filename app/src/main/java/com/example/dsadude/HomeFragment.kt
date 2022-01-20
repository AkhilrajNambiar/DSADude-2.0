package com.example.dsadude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dataStructuresCard = view.findViewById<MaterialCardView>(R.id.data_structures)
        val algorithmsCard = view.findViewById<MaterialCardView>(R.id.algorithms)

        dataStructuresCard.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDataStructuresListFragment()
            findNavController().navigate(action)
        }

        algorithmsCard.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAlgorithmsListFragment()
            findNavController().navigate(action)
        }

    }
}