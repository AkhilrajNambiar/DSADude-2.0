package com.example.dsadude.algorithms.components.sorting.components.bubble_sort

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.dsadude.R
import kotlinx.coroutines.*
import java.lang.Exception

class BubbleSortCanvas(private val mContext: Context): View(mContext) {
    private var backGroundColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var boxColor = ResourcesCompat.getColor(resources, R.color.blue, null)
    private var activeColor = ResourcesCompat.getColor(resources, R.color.Beige1, null)
    private var shifterColor = ResourcesCompat.getColor(resources, R.color.LightGreen1, null)

    private var boxes = mutableListOf<Int>()
    private val uniqueBoxes = mutableSetOf<Int>()

    private lateinit var bb: BubbleSortBoxList

    private val paint1 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = boxColor
        style = Paint.Style.FILL
    }

    private val paint2 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = activeColor
        style = Paint.Style.FILL
    }

    private val paint3 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = shifterColor
        style = Paint.Style.FILL
    }

    init {
        while (uniqueBoxes.size <= 11) {
            uniqueBoxes.add((100..1000).random())
        }
        boxes = uniqueBoxes.toMutableList()
        bb = BubbleSortBoxList(
            boxes,
            -1,
            -1
        )

        GlobalScope.launch {
            bubbleSort(boxes)
        }
    }

//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//
//        if (::extraBitmap.isInitialized) extraBitmap.recycle()
//
//        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//        extraCanvas = Canvas(extraBitmap)
//        extraCanvas.drawColor(backGroundColor)
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(backGroundColor)
        var boxLeft = 10f

        for (i in boxes) {
            if (boxes.indexOf(i) == bb.activeElement) {
                Log.d("bubbleSort", "activeElement value $i, activeIndex ${bb.activeElement}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint2)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.tempElement1) {
                Log.d("bubbleSort", "tempElement1 value $i, tempIndex1 ${bb.tempElement1}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint3)
                boxLeft += 60f
            }
            else {
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint1)
                boxLeft += 60f
            }
        }
    }

    private suspend fun bubbleSort(arr: MutableList<Int>) {
        for (i in 0 until arr.size) {
            for (j in 0 until arr.size - 1) {
                if (arr[j] > arr[j + 1]) {
                    bb.activeElement = j
                    bb.tempElement1 = j + 1
                    delay(500)
                    invalidate()
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    bb.activeElement = j
                    bb.tempElement1 = j + 1
                    delay(500)
                    invalidate()
                }
            }
        }
        bb.activeElement = -1
        bb.tempElement1 = -1
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("bubbleSort", "detached from window!")
    }
}