package com.example.dsadude.algorithms.components.sorting.components.insertion_sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.dsadude.R


class InsertionSortFragment : Fragment(R.layout.fragment_insertion_sort) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val insertionSortVisual = view.findViewById<FrameLayout>(R.id.insertion_sort_visual)
        val insertionSortCanvas = InsertionSortCanvas(requireContext())
        insertionSortVisual.addView(insertionSortCanvas)
    }
}