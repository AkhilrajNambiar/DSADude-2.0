package com.example.dsadude.algorithms.components.sorting.components.bubble_sort

data class BubbleSortBoxList(
    val boxHeights: MutableList<Int>,
    var activeElement: Int,
    var tempElement1: Int
)