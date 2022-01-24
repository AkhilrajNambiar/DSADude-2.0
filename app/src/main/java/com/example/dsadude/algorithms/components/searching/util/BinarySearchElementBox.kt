package com.example.dsadude.algorithms.components.searching.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.dsadude.R
import kotlinx.coroutines.delay

class BinarySearchElementBox(
    private val mContext: Context,
    private val leftOfBox: Float,
    private val topOfBox: Float,
    private val boxWidth: Float,
    val number: String
): View(mContext) {
    private var boxType = BinarySearchBoxType.NORMAL
    private val borderColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private val redBorder = ResourcesCompat.getColor(resources, R.color.PrettyPink, null)
    private val greenBorder = ResourcesCompat.getColor(resources, R.color.LightGreen3, null)
    private val blueBorder = ResourcesCompat.getColor(resources, R.color.BlueViolet3, null)

    private val paint1 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = borderColor
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    private val redPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = redBorder
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    private val greenPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = greenBorder
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    private val bluePaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = blueBorder
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    private val paint2 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = borderColor
        strokeWidth = 2f
        style = Paint.Style.FILL_AND_STROKE
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when(boxType) {
            BinarySearchBoxType.MID -> {
                canvas?.drawRoundRect(leftOfBox, topOfBox, leftOfBox + boxWidth, topOfBox + boxWidth, 25f, 25f, bluePaint)
            }
            BinarySearchBoxType.START -> {
                canvas?.drawRoundRect(leftOfBox, topOfBox, leftOfBox + boxWidth, topOfBox + boxWidth, 25f, 25f, redPaint)
            }
            BinarySearchBoxType.END -> {
                canvas?.drawRoundRect(leftOfBox, topOfBox, leftOfBox + boxWidth, topOfBox + boxWidth, 25f, 25f, greenPaint)
            }
            BinarySearchBoxType.NORMAL -> {
                canvas?.drawRoundRect(leftOfBox, topOfBox, leftOfBox + boxWidth, topOfBox + boxWidth, 25f, 25f, paint1)
            }
        }

        canvas?.drawText(
            number,
            (leftOfBox + leftOfBox + boxWidth) / 2f, // calculated the mean of starting position and ending position
            topOfBox + boxWidth / 2f + paint2.measureText(number, 0, 2) / 2f,
            paint2
        )
    }

    suspend fun setAsStartBox() {
        boxType = BinarySearchBoxType.START
        invalidate()
        delay(500)
    }

    suspend fun setAsEndBox() {
        boxType = BinarySearchBoxType.END
        invalidate()
        delay(500)
    }

    suspend fun setAsMidBox() {
        boxType = BinarySearchBoxType.MID
        invalidate()
        delay(500)
    }

    suspend fun setAsNormalBox() {
        boxType = BinarySearchBoxType.NORMAL
        invalidate()
        delay(20)
    }
}

private enum class BinarySearchBoxType{
    START, END, MID, NORMAL
}