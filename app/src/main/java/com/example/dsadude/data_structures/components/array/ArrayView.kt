package com.example.dsadude.data_structures.components.array

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.dsadude.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar

class ArrayView(
    private val mContext: Context,
): View(mContext) {

    private var firstElementLeft = 0f
    private var firstElementTop = 0f
    private var boxWidth = 0f
    private val whiteColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private val blueColor = ResourcesCompat.getColor(resources, R.color.BlueViolet3, null)
    private val greenColor = ResourcesCompat.getColor(resources, R.color.LightGreen3, null)
    private val arrayValues = mutableListOf<Int>(22, 35, 56, 63, 89, 12, 91, 30, 43)

    private val paint1 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = whiteColor
        strokeWidth = 1f
        textSize = 35f
        style = Paint.Style.FILL_AND_STROKE
        textAlign = Paint.Align.CENTER
    }

    private val paint2 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = blueColor
        strokeWidth = 8f
        style = Paint.Style.STROKE
        textAlign = Paint.Align.CENTER
    }

    private val paint3 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = greenColor
        strokeWidth = 8f
        style = Paint.Style.STROKE
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Whenever onDraw is called you initialize top and left of first element
        boxWidth = width / 6f
        val gap = boxWidth / 5f
        firstElementTop = 50f
        firstElementLeft = 10f
        Log.d("Array", "onDraw called!")
        for ( i in 0 until arrayValues.size ) {
            // Draw the element box
            canvas?.drawRoundRect(firstElementLeft, firstElementTop, firstElementLeft + boxWidth, firstElementTop + boxWidth, 25f, 25f, paint3)
            // Draw the circle to contain the index
            canvas?.drawCircle(firstElementLeft + boxWidth / 2f, firstElementTop + boxWidth + boxWidth/2f, boxWidth / 2f, paint2)
            // Draw the item value within the box
            canvas?.drawText(
                arrayValues[i].toString(),
                (firstElementLeft + firstElementLeft + boxWidth) / 2f, // calculated the mean of starting position and ending position
                firstElementTop + boxWidth / 2f + paint1.measureText("22", 0, 2) / 2f,
                paint1
            )
            // Draw the index within the circle
            canvas?.drawText(
                i.toString(),
                firstElementLeft + boxWidth / 2f, // calculated the mean of starting position and ending position
                firstElementTop + boxWidth + boxWidth/2f + paint1.measureText("22", 0, 2) / 2f,
                paint1
            )
            // Increment the left, by using boxWidth and a gap of 20px
            firstElementLeft += boxWidth + gap
            // If elements don't fit in one line, then go to the next
            if (firstElementLeft + boxWidth > width) {
                firstElementTop += 2 * (boxWidth + gap)
                firstElementLeft = 10f
            }
        }
    }

    fun addItem(item: Int) {
        arrayValues.add(item)
        Log.e("array", item.toString())
        Log.e("array", arrayValues.toString())
        invalidate()
    }

    fun popItem() {
        val removedItem = arrayValues.removeLastOrNull()
        invalidate()
        if ( removedItem != null ) {
            Toast.makeText(mContext, "$removedItem popped!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mContext, "Cannot pop from an empty array!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun positionIsWithinRange(position: Int): Boolean {
        return position < arrayValues.size
    }

    fun insertItem(item: Int, position: Int) {
        if (positionIsWithinRange(position)) {
            arrayValues.add(position, item)
            invalidate()
        }
        else {
            Toast.makeText(mContext, "Position must be less than array size (${getArraySize()})", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getArraySize(): Int {
        return arrayValues.size
    }

    fun removeItemByValue(item: Int){
        val removed =  arrayValues.remove(item)
        invalidate()
        if (removed) {
            Toast.makeText(mContext, "$item has been successfully removed!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(mContext, "$item not found in array!", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeItemByPosition(position: Int) {
        if (positionIsWithinRange(position)) {
            val item =  arrayValues.removeAt(position)
            invalidate()
            Toast.makeText(mContext, "$item has been removed from array", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(mContext, "Position must be less than array size (${getArraySize()})", Toast.LENGTH_SHORT).show()
        }
    }
}