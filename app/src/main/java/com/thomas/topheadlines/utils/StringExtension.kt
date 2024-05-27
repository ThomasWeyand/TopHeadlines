package com.thomas.topheadlines.utils

fun String.getStringUntilDotsInclusive(): String {
    val index = this.indexOf("[")
    return if (index != -1) {
        this.substring(0, index)
    } else {
        this
    }
}