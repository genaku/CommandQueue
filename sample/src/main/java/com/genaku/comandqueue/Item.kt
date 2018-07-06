package com.genaku.comandqueue

/**
 * Created by Gena Kuchergin on 04.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class Item(
        val id: Int,
        val title: String,
        var state: ItemState = ItemState.WHITE,
        var uiState: ItemState = ItemState.WHITE,
        var num: Int = 0,
        var uiNum: Int = 0,
        var progress: String = ""
)