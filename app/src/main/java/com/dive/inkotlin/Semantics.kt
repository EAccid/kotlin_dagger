@file:JvmName("Semantics")

package com.dive.inkotlin

fun Boolean.toInt(): Int {
    if (this)
        return 1
    else
        return 0
}