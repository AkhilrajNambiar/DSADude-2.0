package com.example.dsadude.algorithms.components.sorting.components.quick_sort

import android.content.Context
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

class QuickSortCanvas(private val mContext: Context): View(mContext) {
    private var backGroundColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var boxColor = ResourcesCompat.getColor(resources, R.color.blue, null)
    private var activeColor = ResourcesCompat.getColor(resources, R.color.Beige1, null)
    private var shifterColor = ResourcesCompat.getColor(resources, R.color.LightGreen1, null)
    private var yellowColor = ResourcesCompat.getColor(resources, R.color.OrangeYellow1, null)

    private var boxes = mutableListOf<Int>()

    private val uniqueBoxes = mutableSetOf<Int>()
    private lateinit var bb: QuickSortBoxList

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

    private val paint4 = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = yellowColor
        style = Paint.Style.FILL
    }

    init {
        // To ensure unique elements and hence proper animation
        while (uniqueBoxes.size <= 11) {
            uniqueBoxes.add((100..1000).random())
        }
        boxes = uniqueBoxes.toMutableList()
        Log.d("quickSort", boxes.toString())
        bb = QuickSortBoxList(
            boxes,
            -1,
            -1,
            -1
        )

        GlobalScope.launch {
            quickSort(boxes, 0, boxes.size - 1)
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
            if (boxes.indexOf(i) == bb.pivotElement) {
                Log.d("quickSort", "pivotElement value $i, pIndex ${bb.pivotElement}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint2)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.smallerIndex) {
                Log.d("quickSort", "smallerElement value $i, smallerIndex ${bb.smallerIndex}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint3)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.pIndex) {
                Log.d("quickSort", "pIndex value $i, pIndex ${bb.pIndex}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint4)
                boxLeft += 60f
            }
            else {
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint1)
                boxLeft += 60f
            }
        }
    }

    private suspend fun quickSort(arr: MutableList<Int>, start: Int, end: Int) {
        if (start >= end) {
            return
        }
        val pIndex: Int = partitioner(arr, start, end)
        bb.pIndex = pIndex
        invalidate()
        delay(500)
        quickSort(arr, start, pIndex - 1)
        quickSort(arr, pIndex + 1, end)
        bb.pIndex = -1
        invalidate()
    }

    private suspend fun partitioner(arr: MutableList<Int>, start: Int, end: Int): Int {
        val pivot = arr[end]
//        bb.pivotElement = end
//        invalidate()
//        delay(500)
        var pIndex = start
        for(index in start until end) {
            if (arr[index] <= pivot) {
                // swap the values
//                bb.smallerIndex = index
                bb.pIndex = pIndex
                invalidate()
                delay(500)
                swap(index, pIndex, arr)
//                bb.smallerIndex = index
                bb.pIndex = pIndex
                invalidate()
                delay(500)
                pIndex++
            }
        }
//        bb.pivotElement = end
        bb.pIndex = pIndex
        invalidate()
        delay(500)
        swap(pIndex, end, arr)
//        bb.pivotElement = pIndex
        bb.pIndex = end
        invalidate()
        delay(500)
        return pIndex
    }

    private suspend fun swap(a: Int, b: Int, arr: MutableList<Int>) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }
}