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
import com.example.dsadude.algorithms.components.searching.util.ElementBox
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LinearSearchFragment : Fragment(R.layout.fragment_linear_search) {

    private var leftOfBox = 10f
    private var topOfBox = 150f
    private val boxWidth = 120f
    private var boxes = mutableListOf<Int>()
    private var foundIndex = -1

    private val uniqueBoxes = mutableSetOf<Int>()

    private val elementBoxes = mutableListOf<ElementBox>()

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
        while (uniqueBoxes.size <= 20) {
            uniqueBoxes.add((10..500).random())
        }
        Log.d("screen", "$screenWidth")
        boxes = uniqueBoxes.toMutableList()
        for (i in boxes) {
            val elementBox: ElementBox = ElementBox(requireContext(), leftOfBox, topOfBox, boxWidth, i.toString())
            elementBoxes.add(elementBox)
            leftOfBox += boxWidth + 20
            Log.d("leftOfBox", "leftOfBox: $leftOfBox")
            Log.d("topOfBox", "topOfBox: $topOfBox")
            if ((leftOfBox + boxWidth) > screenWidth) {
                topOfBox += 150f
                leftOfBox = 10f
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
            foundIndex = -1
            foundOrNot.text = ""
            if (itemToSearch.editableText.toString().isEmpty()) {
                Toast.makeText(requireContext(), "No item provided!", Toast.LENGTH_SHORT).show()
            }
            else {
                lifecycleScope.launch {
                    linearSearch(boxes, itemToSearch.editableText.toString().toInt())
                    if (foundIndex == -1) {
                        foundOrNot.text = "Item not found in array!"
                    }
                    else {
                        foundOrNot.text = "Item found at index $foundIndex"
                    }
                }
            }
        }
    }

    private suspend fun linearSearch(nums: List<Int>, target: Int) {
        for (i in nums.indices) {
            if (elementBoxes[i].number.toInt() == target) {
                elementBoxes[i].setAsFoundBox()
                elementBoxes[i].setAsNormalBox()
                foundIndex = i
                break
            }
            else {
                elementBoxes[i].setAsActiveBox()
                elementBoxes[i].setAsNormalBox()
            }
        }
    }
}