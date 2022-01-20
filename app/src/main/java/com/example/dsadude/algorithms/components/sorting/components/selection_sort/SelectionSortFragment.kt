package com.example.dsadude.algorithms.components.sorting.components.selection_sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.dsadude.R

class SelectionSortFragment : Fragment(R.layout.fragment_selection_sort) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectionSortVisual = view.findViewById<FrameLayout>(R.id.selection_sort_visual)
        val selectionSortCanvas = SelectionSortCanvas(requireContext())
        selectionSortVisual.addView(selectionSortCanvas)
    }
}