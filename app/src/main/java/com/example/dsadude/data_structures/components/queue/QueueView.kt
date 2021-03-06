package com.example.dsadude.data_structures.components.queue

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.dsadude.R

class QueueView : View {

    private val mContext: Context

    constructor(mContext: Context) : super(mContext) {
        this.mContext = mContext
        this.whiteColor = ResourcesCompat.getColor(resources, R.color.white, null)
        this.blueColor = ResourcesCompat.getColor(resources, R.color.BlueViolet3, null)
        this.greenColor = ResourcesCompat.getColor(resources, R.color.LightGreen3, null)
        this.redColor = ResourcesCompat.getColor(resources, R.color.PrettyPink, null)
        this.yellowColor = ResourcesCompat.getColor(resources, R.color.SuperYellow, null)
        this.queueValues = mutableListOf<Int>()
        this.paint1 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = whiteColor
            strokeWidth = 1f
            textSize = 35f
            style = Paint.Style.FILL_AND_STROKE
            textAlign = Paint.Align.CENTER
        }
        this.paint2 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = blueColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.paint3 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = greenColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.redPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = redColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.yellowPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = yellowColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
    }

    private var firstElementLeft = 0f
    private var firstElementTop = 0f
    private var boxWidth = 0f
    private val whiteColor: Int
    private val blueColor: Int
    private val greenColor: Int
    private val redColor: Int
    private val yellowColor: Int
    private val queueValues: MutableList<Int>

    private val paint1: Paint

    private val paint2: Paint

    private val paint3: Paint

    private val yellowPaint: Paint

    private val redPaint: Paint

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Whenever onDraw is called you initialize top and left of first element
        boxWidth = width / 6f
        val gap = boxWidth / 5f
        firstElementTop = 50f
        firstElementLeft = 10f
        Log.d("Stack", "onDraw called!")
        for ( i in 0 until queueValues.size ) {
            when(i) {
                queueValues.lastIndex -> {
                    // Draw the rear element box
                    canvas?.drawRoundRect(firstElementLeft, firstElementTop, firstElementLeft + boxWidth, firstElementTop + boxWidth, 25f, 25f, yellowPaint)
                    // Draw the circle to contain the index
                    canvas?.drawCircle(firstElementLeft + boxWidth / 2f, firstElementTop + boxWidth + boxWidth/2f, boxWidth / 2f, paint2)
                    // Draw the item value within the box
                    canvas?.drawText(
                        queueValues[i].toString(),
                        (firstElementLeft + firstElementLeft + boxWidth) / 2f, // calculated the mean of starting position and ending position
                        firstElementTop + boxWidth / 2f + paint1.measureText("22", 0, 2) / 2f,
                        paint1
                    )
                }
                0 -> {
                    // Draw the front element box
                    canvas?.drawRoundRect(firstElementLeft, firstElementTop, firstElementLeft + boxWidth, firstElementTop + boxWidth, 25f, 25f, redPaint)
                    // Draw the circle to contain the index
                    canvas?.drawCircle(firstElementLeft + boxWidth / 2f, firstElementTop + boxWidth + boxWidth/2f, boxWidth / 2f, paint2)
                    // Draw the item value within the box
                    canvas?.drawText(
                        queueValues[i].toString(),
                        (firstElementLeft + firstElementLeft + boxWidth) / 2f, // calculated the mean of starting position and ending position
                        firstElementTop + boxWidth / 2f + paint1.measureText("22", 0, 2) / 2f,
                        paint1
                    )
                }
                else -> {
                    // Draw the element box
                    canvas?.drawRoundRect(firstElementLeft, firstElementTop, firstElementLeft + boxWidth, firstElementTop + boxWidth, 25f, 25f, paint3)
                    // Draw the circle to contain the index
                    canvas?.drawCircle(firstElementLeft + boxWidth / 2f, firstElementTop + boxWidth + boxWidth/2f, boxWidth / 2f, paint2)
                    // Draw the item value within the box
                    canvas?.drawText(
                        queueValues[i].toString(),
                        (firstElementLeft + firstElementLeft + boxWidth) / 2f, // calculated the mean of starting position and ending position
                        firstElementTop + boxWidth / 2f + paint1.measureText("22", 0, 2) / 2f,
                        paint1
                    )
                }
            }
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

    fun enqueue(data: Int) {
        queueValues.add(data)
        Toast.makeText(mContext, "$data enqueued!", Toast.LENGTH_SHORT).show()
        invalidate()
    }

    fun dequeue() {
        val removed = queueValues.removeFirstOrNull()
        if (removed == null) {
            Toast.makeText(mContext, "Queue is empty!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(mContext, "$removed dequeued!", Toast.LENGTH_SHORT).show()
        }
        invalidate()
    }

    constructor(mContext: Context, attributeSet: AttributeSet) : super(mContext, attributeSet) {
        this.mContext = mContext
        this.whiteColor = ResourcesCompat.getColor(resources, R.color.white, null)
        this.blueColor = ResourcesCompat.getColor(resources, R.color.BlueViolet3, null)
        this.greenColor = ResourcesCompat.getColor(resources, R.color.LightGreen3, null)
        this.redColor = ResourcesCompat.getColor(resources, R.color.PrettyPink, null)
        this.yellowColor = ResourcesCompat.getColor(resources, R.color.SuperYellow, null)
        this.queueValues = mutableListOf<Int>()
        this.paint1 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = whiteColor
            strokeWidth = 1f
            textSize = 35f
            style = Paint.Style.FILL_AND_STROKE
            textAlign = Paint.Align.CENTER
        }
        this.paint2 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = blueColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.paint3 = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = greenColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.redPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = redColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
        this.yellowPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = yellowColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
            textAlign = Paint.Align.CENTER
        }
    }
}