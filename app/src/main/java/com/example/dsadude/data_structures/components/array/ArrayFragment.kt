package com.example.dsadude.data_structures.components.array

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dsadude.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ArrayFragment : Fragment() {

    private val boxWidth = 120f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_array, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortingLabel = view.findViewById<TextView>(R.id.sorting_label)
        val visualContainer = view.findViewById<FrameLayout>(R.id.visual_container)
        sortingLabel.text = resources.getString(R.string.dynamic_array)

        val arrayView = ArrayView(requireContext())
        visualContainer.addView(arrayView)

        val performOperation = view.findViewById<Button>(R.id.perform_operation)
        val itemValueField = view.findViewById<TextInputLayout>(R.id.item)
        val itemValue = view.findViewById<TextInputEditText>(R.id.item_text)
        val itemPositionField = view.findViewById<TextInputLayout>(R.id.position_in_array)
        val itemPosition = view.findViewById<TextInputEditText>(R.id.position_in_array_text)
        val operations = view.findViewById<TextInputLayout>(R.id.array_operations)
        val operationName = view.findViewById<AutoCompleteTextView>(R.id.operation_name)
        val helperText = view.findViewById<TextView>(R.id.found_or_not)

        val operationsList = resources.getStringArray(R.array.array_operations)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, operationsList)
        operationName.setAdapter(arrayAdapter)

        operationName.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                when (adapterView?.getItemAtPosition(position)) {
                    "Append" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.VISIBLE
                    }
                    "Insert" -> {
                        itemPositionField.visibility = View.VISIBLE
                        itemValueField.visibility = View.VISIBLE
                    }
                    "Pop" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.GONE
                    }
                    "Remove" -> {
                        itemPositionField.visibility = View.VISIBLE
                        itemValueField.visibility = View.VISIBLE
                    }
                }
            }

        performOperation.setOnClickListener {
            when(operationName.text.toString()) {
                "Append" -> {
                    if (itemValue.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Item value is required to append!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (checkNumRegex(itemValue.text.toString())) {
                            arrayView.addItem(itemValue.text.toString().toFloat().toInt())
                            Toast.makeText(requireContext(), "Append ${itemValue.text.toString().toFloat().toInt()} to array", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(requireContext(), "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "Insert" -> {
                    if (itemValue.text.isNullOrEmpty() || itemPosition.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Item value and position to insert is required!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (checkNumRegex(itemValue.text.toString()) && checkNumRegex(itemPosition.text.toString())) {
                            arrayView.insertItem(
                                itemValue.text.toString().toFloat().toInt(),
                                itemPosition.text.toString().toFloat().toInt()
                            )
                        }
                        else {
                            Toast.makeText(requireContext(), "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "Pop" -> {
                    arrayView.popItem()
                }
                "Remove" -> {
                    if (!itemValue.text.isNullOrEmpty() && !itemPosition.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Give only item value or position but not both!", Toast.LENGTH_SHORT).show()
                    }
                    else if (itemValue.text.isNullOrEmpty() && itemPosition.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Give either item value or position to remove!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (!itemValue.text.isNullOrEmpty()) {
                            arrayView.removeItemByValue(itemValue.text.toString().toFloat().toInt())
                        }
                        else {
                            arrayView.removeItemByPosition(itemPosition.text.toString().toFloat().toInt())
                        }
                    }
                }
                else -> {
                    Toast.makeText(requireContext(), "Please select an operation to perform!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkNumRegex(item: String): Boolean {
        return item.matches(Regex("^\\d*\\.?\\d+\$"))
    }
}