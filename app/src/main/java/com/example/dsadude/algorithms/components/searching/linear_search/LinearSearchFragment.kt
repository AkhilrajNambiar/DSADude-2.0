package com.example.dsadude.algorithms.components.searching.linear_search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.dsadude.R
import com.example.dsadude.algorithms.components.searching.util.LinearSearchElementBox
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LinearSearchFragment : Fragment(R.layout.fragment_linear_search) {

    private var leftOfBox = 20f
    private var topOfBox = 100f
    private var boxWidth = 0f
    private var boxes = mutableListOf<Int>()
    private var foundIndex = -1
    private var isRunning = false

    private val uniqueBoxes = mutableSetOf<Int>()

    private val elementBoxes = mutableListOf<LinearSearchElementBox>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_linear_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortingLabel = view.findViewById<TextView>(R.id.sorting_label)
        val visualContainer = view.findViewById<FrameLayout>(R.id.visual_container)

        val screenWidth = requireActivity().windowManager.defaultDisplay.width
        val screenHeight = requireActivity().windowManager.defaultDisplay.height

        boxWidth = screenWidth / 6f

        while (uniqueBoxes.size <= 24) {
            uniqueBoxes.add((10..500).random())
        }
        Log.d("screen", "$screenWidth")
        boxes = uniqueBoxes.toMutableList()
        for (i in boxes) {
            val linearSearchElementBox: LinearSearchElementBox = LinearSearchElementBox(requireContext(), leftOfBox, topOfBox, boxWidth, i.toString())
            elementBoxes.add(linearSearchElementBox)
            leftOfBox += boxWidth + 20
            Log.d("leftOfBox", "leftOfBox: $leftOfBox")
            Log.d("topOfBox", "topOfBox: $topOfBox")
            if ((leftOfBox + boxWidth) > screenWidth) {
                topOfBox += boxWidth + 30f
                leftOfBox = 20f
            }
        }
        Log.d("boxes", "boxes: $boxes")
        Log.d("elementBoxes", "elementBoxes: $elementBoxes")
        for (i in elementBoxes) {
            visualContainer.addView(i)
        }
        val startSearchingBtn = view.findViewById<Button>(R.id.start_searching)
        val itemToSearch = view.findViewById<TextInputEditText>(R.id.number_to_search_text)
        val foundOrNot = view.findViewById<TextView>(R.id.found_or_not)

        startSearchingBtn.setOnClickListener {
            if (!isRunning) {
                foundIndex = -1
                foundOrNot.text = ""
                if (itemToSearch.editableText.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "No item provided!", Toast.LENGTH_SHORT).show()
                }
                else {
                    lifecycleScope.launch {
                        linearSearch(boxes, itemToSearch.editableText.toString().toInt())
                        if (foundIndex == -1) {
                            foundOrNot.text = resources.getString(R.string.item_not_found)
                            Log.d("foundIndex", "Item not found!")
                        }
                        else {
                            foundOrNot.text = resources.getString(R.string.item_found, foundIndex)
                            Log.d("foundIndex", "Item found at index $foundIndex")
                        }
                    }
                }
            }
            else {
                Toast.makeText(requireContext(), "One search process is still going on!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun linearSearch(nums: List<Int>, target: Int) {
        isRunning = true
        for (i in nums.indices) {
            if (elementBoxes[i].number.toInt() == target) {
                elementBoxes[i].setAsFoundBox()
                elementBoxes[i].setAsNormalBox()
                foundIndex = i
                isRunning = false
                break
            }
            else {
                elementBoxes[i].setAsActiveBox()
                elementBoxes[i].setAsNormalBox()
            }
        }
        isRunning = false
    }
}