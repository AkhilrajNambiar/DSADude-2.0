package com.example.dsadude.algorithms.components.sorting.components.merge_sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.dsadude.R


class MergeSortFragment : Fragment(R.layout.fragment_merge_sort) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mergeSortVisual = view.findViewById<FrameLayout>(R.id.merge_sort_visual)
        val mergeSortCanvas = MergeSortCanvas(requireContext())
        mergeSortVisual.addView(mergeSortCanvas)
    }
}