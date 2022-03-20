package com.example.dsadude.data_structures.components.stack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dsadude.R
import com.example.dsadude.data_structures.components.array.ArrayView
import com.example.dsadude.data_structures.util.Helper
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class StackFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortingLabel = view.findViewById<TextView>(R.id.sorting_label)
        val visualContainer = view.findViewById<FrameLayout>(R.id.visual_container)
        val stackView = StackView(requireContext())
        visualContainer.addView(stackView)
        sortingLabel.text = resources.getString(R.string.stack)

        val performOperation = view.findViewById<Button>(R.id.perform_operation)
        val itemValueField = view.findViewById<TextInputLayout>(R.id.item)
        val itemValue = view.findViewById<TextInputEditText>(R.id.item_text)
        val itemPositionField = view.findViewById<TextInputLayout>(R.id.position_in_array)
        val itemPosition = view.findViewById<TextInputEditText>(R.id.position_in_array_text)
        val operations = view.findViewById<TextInputLayout>(R.id.array_operations)
        val operationName = view.findViewById<AutoCompleteTextView>(R.id.operation_name)
        val helperText = view.findViewById<TextView>(R.id.found_or_not)

        val operationsList = resources.getStringArray(R.array.stack_operations)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, operationsList)
        operationName.setAdapter(arrayAdapter)

        operationName.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                when (adapterView?.getItemAtPosition(position)) {
                    "Push" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.VISIBLE
                    }
                    "Pop" -> {
                        itemPositionField.visibility = View.GONE
                        itemValueField.visibility = View.GONE
                    }
                }
            }
        performOperation.setOnClickListener {
            when(operationName.text.toString()) {
                "Push" -> {
                    if (itemValue.text.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "Item value is required to append!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (Helper.checkNumRegex(itemValue.text.toString())) {
                            stackView.push(itemValue.text.toString().toFloat().toInt())
                        }
                        else {
                            Toast.makeText(requireContext(), "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "Pop" -> {
                    stackView.pop()
                }
            }
        }
    }
}