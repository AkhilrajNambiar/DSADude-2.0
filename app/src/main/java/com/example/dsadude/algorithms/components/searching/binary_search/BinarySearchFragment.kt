package com.example.dsadude.algorithms.components.searching.binary_search

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
import com.example.dsadude.algorithms.components.searching.util.BinarySearchElementBox
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class BinarySearchFragment : Fragment(R.layout.fragment_binary_search) {
    private var leftOfBox = 10f
    private var topOfBox = 75f
    private val boxWidth = 120f
    private var boxes = mutableListOf<Int>()
    private var foundIndex = -1
    private var prevMid = 0
    private var isRunning = false

    private val uniqueBoxes = mutableSetOf<Int>()

    private val elementBoxes = mutableListOf<BinarySearchElementBox>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_binary_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val visualContainer = view.findViewById<FrameLayout>(R.id.visual_container)
        val screenWidth = requireActivity().windowManager.defaultDisplay.width
        val screenHeight = requireActivity().windowManager.defaultDisplay.height
        while (uniqueBoxes.size <= 24) {
            uniqueBoxes.add((10..500).random())
        }
        Log.d("screen", "$screenWidth")
        boxes = uniqueBoxes.toMutableList()
        boxes.sort()

        for (i in boxes) {
            val elementBox = BinarySearchElementBox(requireContext(), leftOfBox, topOfBox, boxWidth, i.toString())
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
            if (!isRunning) {
                foundIndex = -1
                foundOrNot.text = ""
                if (itemToSearch.editableText.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "No item provided!", Toast.LENGTH_SHORT).show()
                }
                else {
                    lifecycleScope.launch {
                        binarySearch(boxes, itemToSearch.editableText.toString().toInt())
                        Log.d("binarySearch", "foundIndex $foundIndex")
                        if (foundIndex == -1) {
                            foundOrNot.text = resources.getString(R.string.item_not_found)
                        }
                        else {
                            foundOrNot.text = resources.getString(R.string.item_found, foundIndex)
                        }
                    }
                }
            }
            else {
                Toast.makeText(requireContext(), "One search process is still going on!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun binarySearch(arr: List<Int>, target: Int){
        // Searching has started
        isRunning = true
        var prevStart = 0
        var prevEnd = 0
        var start = 0
        var end = arr.size - 1
        var mid = 0
        // The itemFound variable helps after the end of while loop
        // if the while loop breaks after finding mid, then its value
        // is true, else if while loop ends normally then itemFound is
        // false. This additional check helps in reducing the delay
        // that happens at the end of while loop
        var itemFound = false
        // If target is too small stop searching and return
        if (target < arr[start]) {
            isRunning = false
            return
        }
        // if target is too big then stop searching and return
        else if (target > arr[end]) {
            isRunning = false
            return
        }
        Log.d("binarySearch", "target $target")
        elementBoxes[start].setAsStartBox()
        elementBoxes[end].setAsEndBox()
        while (start <= end) {
            elementBoxes[start].setAsStartBox()
            elementBoxes[end].setAsEndBox()
            // prevMid unselects the previous mid valued box
            // same is done by prevStart and prevEnd
            prevMid = mid
            mid = (start + end) / 2
            elementBoxes[prevMid].setAsNormalBox()
            elementBoxes[mid].setAsMidBox()
            if (arr[mid] == target) {
                foundIndex = mid
                itemFound = true
                elementBoxes[mid].setAsNormalBox()
                elementBoxes[start].setAsNormalBox()
                elementBoxes[end].setAsNormalBox()
                break
            }
            if (target > arr[mid]) {
                prevStart = start
                start = mid + 1
                elementBoxes[prevStart].setAsNormalBox()
                elementBoxes[start].setAsStartBox()
            }
            else {
                prevEnd = end
                end = mid - 1
                elementBoxes[prevEnd].setAsNormalBox()
                elementBoxes[end].setAsEndBox()
            }
        }
        if (!itemFound) {
            elementBoxes[mid].setAsNormalBox()
            elementBoxes[start].setAsNormalBox()
            elementBoxes[end].setAsNormalBox()
        }
        // searching has stopped
        isRunning = false
    }
}