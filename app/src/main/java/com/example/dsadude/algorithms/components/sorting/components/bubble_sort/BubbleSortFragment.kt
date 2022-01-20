package com.example.dsadude.algorithms.components.sorting.components.bubble_sort

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.dsadude.MainActivity
import com.example.dsadude.R

class BubbleSortFragment : Fragment(R.layout.fragment_bubble_sort) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bubbleSortContainer = view.findViewById<FrameLayout>(R.id.bubble_sort_visual)
        val bubbleSortCanvas = BubbleSortCanvas(requireContext())
        bubbleSortContainer.addView(bubbleSortCanvas)
    }
}