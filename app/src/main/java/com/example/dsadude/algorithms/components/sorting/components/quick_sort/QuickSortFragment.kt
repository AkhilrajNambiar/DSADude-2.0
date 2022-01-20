package com.example.dsadude.algorithms.components.sorting.components.quick_sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.dsadude.R

class QuickSortFragment : Fragment(R.layout.fragment_quick_sort) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val quickSortVisual = view.findViewById<FrameLayout>(R.id.quick_sort_visual)
        val quickSortCanvas = QuickSortCanvas(requireContext())
        quickSortVisual.addView(quickSortCanvas)
    }
}