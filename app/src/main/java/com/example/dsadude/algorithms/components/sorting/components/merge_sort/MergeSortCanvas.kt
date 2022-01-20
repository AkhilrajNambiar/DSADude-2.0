package com.example.dsadude.algorithms.components.sorting.components.merge_sort

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

class MergeSortCanvas(private val mContext: Context): View(mContext) {
    private val music = MediaPlayer.create(this.context, R.raw.swoosh)
    private var backGroundColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var boxColor = ResourcesCompat.getColor(resources, R.color.blue, null)
    private var activeColor = ResourcesCompat.getColor(resources, R.color.Beige1, null)
    private var shifterColor = ResourcesCompat.getColor(resources, R.color.LightGreen1, null)

    private val uniqueBoxes = mutableSetOf<Int>()
    private var boxes = mutableListOf<Int>()

    private lateinit var bb: MergeSortBoxList

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
        bb = MergeSortBoxList(
            boxes,
            -1,
            -1
        )

        GlobalScope.launch {
            mergeSort(boxes, 0, boxes.size - 1)
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
                Log.d("mergeSort", "activeElement value $i, activeIndex ${bb.activeElement}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint2)
                boxLeft += 60f
            }
            else if (boxes.indexOf(i) == bb.tempElement1) {
                Log.d("mergeSort", "tempElement1 value $i, tempIndex1 ${bb.tempElement1}")
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint3)
                boxLeft += 60f
            }
            else {
                canvas?.drawRect(boxLeft, (height - i).toFloat(), boxLeft+50, height - 10f, paint1)
                boxLeft += 60f
            }
        }
    }

    private suspend fun mergeSort(arr: MutableList<Int>, p: Int, q: Int) {
        if (p < q) {
            val mid = (p+q)/2
            mergeSort(arr, p, mid)
            mergeSort(arr, mid+1, q)
            merge(arr, p, mid, mid+1, q)
        }
    }

    private suspend fun merge(arr: MutableList<Int>, p: Int, q: Int, r: Int, s: Int) {
        val n1 = q - p + 1
        val n2 = s - r + 1
        // creating two arrays left and right
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        // copy the elements' arr[p...q] to left
        for (i in p..q){
            left.add(arr[i])
        }
        // copy the elements' arr[r...s] to right
        for (j in r..s) {
            right.add(arr[j])
        }
        // also add sentinel values to check if the array has become empty
        left.add(Int.MAX_VALUE)
        right.add(Int.MAX_VALUE)
        var i = 0
        var j = 0
        var k = p
        while (k <= s) {
            bb.activeElement = k
            invalidate()
            if (left[i] <= right[j]) {
                invalidate()
                arr[k] = left[i]
                invalidate()
                delay(400)
                Log.d("mergeSort", "k: $k")
                Log.d("mergeSort", "i: $i")
                k++
                bb.activeElement = k
                Log.d("mergeSort", "k: $k")
                invalidate()
                delay(400)
                i++
                Log.d("mergeSort", "i: $i")
            }
            else {
                invalidate()
                arr[k] = right[j]
                invalidate()
                delay(400)
                Log.d("mergeSort", "k: $k")
                Log.d("mergeSort", "j: $j")
                k++
                bb.activeElement = k
                invalidate()
                Log.d("mergeSort", "k: $k")
                delay(400)
                j++
                Log.d("mergeSort", "j: $j")
            }
        }
    }
}