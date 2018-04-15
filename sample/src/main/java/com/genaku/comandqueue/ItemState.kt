package com.genaku.comandqueue

/**
 * Created by Gena Kuchergin on 04.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
enum class ItemState {
    WHITE,
    GREEN,
    RED,
    BLUE
}

inline fun <reified T : Enum<T>> T.next(): T {
    val values = enumValues<T>()
    val nextOrdinal = (ordinal + 1) % values.size
    return values[nextOrdinal]
}