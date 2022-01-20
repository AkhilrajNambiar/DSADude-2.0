package com.example.dsadude.algorithms.components.sorting.components.quick_sort

data class QuickSortBoxList(
    val boxHeights: MutableList<Int>,
    var pivotElement: Int,
    var smallerIndex: Int,
    var pIndex: Int
)