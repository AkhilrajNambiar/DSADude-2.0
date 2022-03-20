package com.example.dsadude.data_structures.components.linked_list

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import com.example.dsadude.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class Node(var data: Int, var next: Node? = null)

class LinkedListView(private val mContext: Context): View(mContext) {

    private var head: Node? = null
    private var currentNode: Node? = null
    private var foundNode: Node? = null
    private var isSearchOn: Boolean = false
    private var boxWidth = 0f


    private val greenColor = ResourcesCompat.getColor(resources, R.color.LightGreen3, null)
    private val redColor = ResourcesCompat.getColor(resources, R.color.PrettyPink, null)
    private val blueColor = ResourcesCompat.getColor(resources, R.color.BlueViolet3, null)
    private val whiteColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var firstElementTop = 0f
    private var firstElementLeft = 50f

    private val paint1 = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = blueColor
        strokeWidth = 10f
    }

    private val greenPaint = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = greenColor
        strokeWidth = 10f
    }

    private val redPaint = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = redColor
        strokeWidth = 10f
    }

    private val paint2 = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        color = whiteColor
        strokeWidth = 10f
    }

    private val paint3 = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        color = whiteColor
        strokeWidth = 2f
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    private val path = Path()

    private val headBitmap = BitmapFactory.decodeResource(resources, R.drawable.megahead)
    private val nullIndicator = BitmapFactory.decodeResource(resources, R.drawable.stop)
    private val smallNull = Bitmap.createScaledBitmap(nullIndicator, 150, 150, false)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        boxWidth = width / 7.5f

        firstElementLeft = boxWidth / 3f
        firstElementTop = (7 * boxWidth) / 3f

        var p = head

        Log.d("Megamind",
            "width: ${headBitmap.width} , half-width: ${headBitmap.width / 2f}"
        )

        createArrowHead(
            path,
            (firstElementLeft + headBitmap.width)/2f,
            firstElementTop - 10,
            VerticalOrHorizontal.VERTICAL,
            10
        )
        canvas?.drawPath(path, paint2)
        canvas?.drawLine(
            (firstElementLeft + headBitmap.width)/2f,
            headBitmap.height.toFloat(),
            (firstElementLeft + headBitmap.width)/2f,
            firstElementTop - 10,
            paint2
        )
        canvas?.drawBitmap(headBitmap.scale(boxWidth.toInt(), boxWidth.toInt(), false), firstElementLeft, firstElementTop - headBitmap.height, null)
        while (p != null) {
            when (p) {
                currentNode -> {
                    drawAndUpdateItem(canvas, p.data, width, NodeState.CURRENT)
                }
                foundNode -> {
                    drawAndUpdateItem(canvas, p.data, width, NodeState.FOUND)
                }
                else -> {
                    drawAndUpdateItem(canvas, p.data, width, NodeState.NORMAL)
                }
            }
            p = p.next
        }
        canvas?.drawBitmap(smallNull.scale(boxWidth.toInt(), boxWidth.toInt(), false), firstElementLeft, firstElementTop, null)
    }

    private fun drawAndUpdateItem(canvas: Canvas?, data: Int, width: Int, state: NodeState) {
        /** Right is left + box width
         * For showing the pointer however add an extra 100 to
         * elementRight */
        // I had initially assumed boxWidth to be 150f
        var elementRight = firstElementLeft + boxWidth
        var elementBottom = firstElementTop + boxWidth
        // 100 is 2/3 of 150
        if (elementRight + (2 * boxWidth / 3) >= width) {
            firstElementLeft = boxWidth / 3
            // Since I had used 220 over here
            firstElementTop += (22 * boxWidth) / 15f
            // Reset the right of the box and the bottom of the box
            elementRight = firstElementLeft + boxWidth
            elementBottom = firstElementTop + boxWidth
            val halfArrowHeadX = firstElementLeft - 20
            val halfArrowHeadY = (firstElementTop + elementBottom) / 2
            createArrowHead(
                path,
                halfArrowHeadX,
                halfArrowHeadY,
                VerticalOrHorizontal.HORIZONTAL,
                10
            )
            canvas?.drawLine(0f, halfArrowHeadY, halfArrowHeadX, halfArrowHeadY, paint2)
        }
        when(state) {
            NodeState.NORMAL -> {
                canvas?.drawRect(firstElementLeft, firstElementTop, elementRight, elementBottom, paint1)
                canvas?.drawRect(elementRight, firstElementTop, elementRight + (2 * boxWidth / 3), elementBottom, paint1)
            }
            NodeState.CURRENT -> {
                canvas?.drawRect(firstElementLeft, firstElementTop, elementRight, elementBottom, redPaint)
                canvas?.drawRect(elementRight, firstElementTop, elementRight + (2 * boxWidth / 3), elementBottom, redPaint)
            }
            NodeState.FOUND -> {
                canvas?.drawRect(firstElementLeft, firstElementTop, elementRight, elementBottom, greenPaint)
                canvas?.drawRect(elementRight, firstElementTop, elementRight + (2 * boxWidth / 3), elementBottom, greenPaint)
            }
        }
        // 200 is 4/3 of 150
        val arrowHeadX = elementRight + (4 * boxWidth / 3)
        val arrowHeadY = (firstElementTop + elementBottom) / 2
        // To draw the arrow head
        createArrowHead(path, arrowHeadX, arrowHeadY, VerticalOrHorizontal.HORIZONTAL, 10)
        canvas?.drawPath(path, paint2)
        canvas?.drawLine(elementRight + (2 * boxWidth / 3), arrowHeadY, arrowHeadX, arrowHeadY, paint2)
        canvas?.drawText(
            data.toString(),
            (firstElementLeft + elementRight) / 2f, // calculated the mean of starting position and ending position
            firstElementTop + boxWidth / 2f + paint3.measureText("22", 0, 2) / 2f,
            paint3
        )
        firstElementLeft = arrowHeadX + 20
    }

    private fun createArrowHead(
        path: Path,
        x: Float,
        y: Float,
        verticalOrHorizontal: VerticalOrHorizontal,
        size: Int
    ) {
        Log.d("kingkong", "arrowHeadX: $x ; arrowHeadY: $y")
        if (verticalOrHorizontal == VerticalOrHorizontal.HORIZONTAL) {
            path.moveTo(x, y)
            path.lineTo(x, y - size)
            path.lineTo(x + size, y)
            path.lineTo(x, y + size)
            path.lineTo(x, y)
        }
        else if (verticalOrHorizontal == VerticalOrHorizontal.VERTICAL) {
            path.moveTo(x, y)
            path.lineTo(x - size, y - size)
            path.lineTo(x + size, y - size)
            path.lineTo(x, y)
        }
    }

    fun addItemToStart(data: Int) {
        val node = Node(data)
        val p: Node? = head
        if (p == null) {
            head = node
            return
        }
        node.next = head
        head = node
        invalidate()
    }

    fun addItemToEnd(data: Int) {
        val node = Node(data)
        var p: Node? = head
        if (p == null) {
            head = node
            return
        }
        while (p?.next != null) {
            p = p.next
        }
        p?.next = node
        invalidate()
    }

    fun deleteFromFront() {
        if (head == null) {
            println("Can't delete from empty list!")
            return
        }
        val data = head?.data
        head = head?.next
        println("$data has been removed successfully!")
        invalidate()
    }

    fun deleteFromEnd() {
        if (head == null) {
            println("Can't delete from empty list!")
            return
        }
        else if (head?.next == null) {
            val removedData = head?.data
            head = null
            println("$removedData has been removed successfully!")
            invalidate()
            return
        }
        var p = head!!
        // move till the second last node
        while (p.next?.next != null) {
            p = p.next!!
        }
        val removedData = p.next?.data
        p.next = null
        println("$removedData has been removed successfully!")
        invalidate()
    }

    suspend fun search(data: Int) {
        delay(50)
        isSearchOn = true
        if (head == null) {
            println("Nothing to search in an empty list!")
            return
        }
        var index = 0
        var p = head
        while (p != null) {
            currentNode = p
            invalidate()
            delay(500)
            if (p.data == data) {
                currentNode = null
                foundNode = p
                invalidate()
                delay(500)
                Toast.makeText(mContext, "$data found at position $index", Toast.LENGTH_SHORT).show()
                isSearchOn = false
                foundNode = null
                invalidate()
                delay(500)
                return
            }
            p = p.next
            index++
        }
        currentNode = null
        foundNode = null
        invalidate()
        delay(500)
        println("$data not found in this list!")
        isSearchOn = false
        Toast.makeText(mContext, "$data not found in linked list!", Toast.LENGTH_SHORT).show()
    }

    private fun searchOnly(data: Int): Int{
        if (head == null) {
            println("Nothing to search in an empty list!")
            return -1
        }
        var index = 0
        var p = head
        while (p != null) {
            if (p.data == data) {
                println("$data found at position $index")
                return index
            }
            p = p.next
            index++
        }
        println("$data not found in this list!")
        return -1
    }

    enum class VerticalOrHorizontal {
        VERTICAL, HORIZONTAL
    }

    enum class NodeState {
        NORMAL, CURRENT, FOUND
    }
}