package com.example.dsadude.data_structures.components.linked_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.dsadude.R
import com.example.dsadude.data_structures.util.Helper.Companion.checkNumRegex
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


class LinkedListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_linked_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortingLabel = view.findViewById<TextView>(R.id.sorting_label)
        val visualContainer = view.findViewById<FrameLayout>(R.id.visual_container)
        val llView = LinkedListView(requireContext())
        visualContainer.addView(llView)
        sortingLabel.text = "Linked List"

        val performOperation = view.findViewById<Button>(R.id.perform_operation)
        val itemValueField = view.findViewById<TextInputLayout>(R.id.item)
        val itemValue = view.findViewById<TextInputEditText>(R.id.item_text)
        val itemPositionField = view.findViewById<TextInputLayout>(R.id.position_in_array)
        val itemPosition = view.findViewById<TextInputEditText>(R.id.position_in_array_text)
        val operations = view.findViewById<TextInputLayout>(R.id.array_operations)
        val operationName = view.findViewById<AutoCompleteTextView>(R.id.operation_name)
        val helperText = view.findViewById<TextView>(R.id.found_or_not)

        val operationsList = resources.getStringArray(R.array.ll_operations)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, operationsList)
        operationName.setAdapter(arrayAdapter)

        operationName.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                when (adapterView?.getItemAtPosition(position)) {
                    "Add to Front" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.VISIBLE
                    }
                    "Add to End" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.VISIBLE
                    }
                    "Delete from Front" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.GONE
                    }
                    "Delete from End" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.GONE
                    }
                    "Search" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.VISIBLE
                    }
                }
            }
        performOperation.setOnClickListener {
            when(operationName.text.toString()) {
                "Add to Front" -> {
                    if (itemValue.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Item value is required!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (checkNumRegex(itemValue.text.toString())) {
                            llView.addItemToStart(itemValue.text.toString().toFloat().toInt())
                            Toast.makeText(requireContext(), "Added ${itemValue.text.toString().toFloat().toInt()} to linked list", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(requireContext(), "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "Add to End" -> {
                    if (itemValue.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Item value is required!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (checkNumRegex(itemValue.text.toString())) {
                            llView.addItemToEnd(itemValue.text.toString().toFloat().toInt())
                            Toast.makeText(requireContext(), "Added ${itemValue.text.toString().toFloat().toInt()} to linked list", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(requireContext(), "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "Delete from Front" -> {
                    llView.deleteFromFront()
                }
                "Delete from End" -> {
                    llView.deleteFromEnd()
                }
                "Search" -> {
                    Toast.makeText(requireContext(), "Waiting for implementation", Toast.LENGTH_SHORT).show()
                    hideSoftKeyboard()
                    lifecycleScope.launch {
                        llView.search(itemValue.text.toString().toFloat().toInt())
                    }
                }
                else -> {
                    Toast.makeText(requireContext(), "Please select an operation to perform!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun hideSoftKeyboard() {
        // get the view that currently has focus
        val viewWithFocus = requireActivity().currentFocus
        // get Input Method Manager
        val manager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        // hide soft keyboard
        manager.hideSoftInputFromWindow(viewWithFocus?.windowToken, 0)
    }
}