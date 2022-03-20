package com.example.dsadude.data_structures.util

class Helper {
    companion object {
        fun checkNumRegex(item: String): Boolean {
            return item.matches(Regex("^\\d*\\.?\\d+\$"))
        }
    }
}