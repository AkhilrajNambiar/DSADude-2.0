package com.example.dsadude.algorithms.components.sorting.components.selection_sort

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

class SelectionSortCanvas(private val mContext: Context): View(mContext) {
    private var backGroundColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var boxColor = ResourcesCompat.getColor(resources, R.color.blue, null)
    private var activeColor = ResourcesCompat.getColor(resources, R.color.Beige1, null)
    private var shifterColor = ResourcesCompat.getColor(resources, R.color.LightGreen1, null)

    private var boxes = mutableListOf<Int>()

    private val uniqueBoxes = mutableSetOf<Int>()

    private lateinit var bb: SelectionSortBoxList

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
        bb = SelectionSortBoxList(
            boxes,
            -1,
            -1
        )

        GlobalScope.launch {
            selectionSort(boxes)
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
            if (boxes.indexOf(i) == bb.minIndex) {
                Log.d("selectionSort", "minimum value $i, minIndex ${bb.minIndex}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint2)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.currIndex) {
                Log.d("selectionSort", "current value $i, tempIndex1 ${bb.currIndex}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint3)
                boxLeft += 60f
            }
            else {
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint1)
                boxLeft += 60f
            }
        }
    }

    private suspend fun selectionSort(arr: MutableList<Int>) {
        for (i in 0 until arr.size - 1) {
            var minIndex = i
            for (j in i + 1 until arr.size) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            if (i != minIndex) {
                bb.currIndex = i
                bb.minIndex = minIndex
                invalidate()
                delay(500)
                val temp = arr[minIndex]
                arr[minIndex] = arr[i]
                arr[i] = temp
                bb.currIndex = i
                bb.minIndex = minIndex
                invalidate()
                delay(500)
            }
        }
        bb.currIndex = -1
        bb.minIndex = -1
        invalidate()
    }
}