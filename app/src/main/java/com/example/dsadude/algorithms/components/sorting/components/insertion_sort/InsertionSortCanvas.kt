package com.example.dsadude.algorithms.components.sorting.components.insertion_sort

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.dsadude.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InsertionSortCanvas(private val mContext: Context): View(mContext) {

    private var backGroundColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var boxColor = ResourcesCompat.getColor(resources, R.color.blue, null)
    private var activeColor = ResourcesCompat.getColor(resources, R.color.Beige1, null)
    private var shifterColor = ResourcesCompat.getColor(resources, R.color.LightGreen1, null)

    private var boxes = mutableListOf<Int>()
    private val uniqueBoxes = mutableSetOf<Int>()

    private lateinit var bb: InsertionSortBoxList

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
        bb = InsertionSortBoxList(
            boxes,
            -1,
            -1
        )

        GlobalScope.launch {
            insertionSort(boxes)
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
                Log.d("insertionSort", "activeElement value $i, activeIndex ${bb.activeElement}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint2)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.tempElement1) {
                Log.d("insertionSort", "tempElement1 value $i, tempIndex1 ${bb.tempElement1}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint3)
                boxLeft += 60f
            }
            else {
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint1)
                boxLeft += 60f
            }
        }
    }

    private suspend fun insertionSort(arr: MutableList<Int>) {
        for (j in 1 until arr.size) {
            val key = arr[j]
            var i = j - 1
            bb.activeElement = j
            bb.tempElement1 = i
            delay(500)
            invalidate()
            while (i >= 0 && arr[i] > key) {
                arr[i+1] = arr[i]
                Log.d("shifterElement", "i: $i")
                Log.d("activeElement", "j: $j")
                delay(500)
                invalidate()
                i -= 1
                bb.tempElement1 = i
                delay(500)
                invalidate()
            }
            arr[i+1] = key
            bb.tempElement1 = i + 1
            delay(500)
            invalidate()
        }
        bb.activeElement = -1
        bb.tempElement1 = -1
    }
}