package com.genaku.comandqueue

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.genaku.commandqueue.CommandProcessor
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by Gena Kuchergin on 06.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class ExampleViewModel : ViewModel() {

    private var commands = CommandProcessor<Int>()
    private val mItems = getDataSet()
    val items = MutableLiveData<CopyOnWriteArrayList<Item>>()
    var mResettingCommander = true

    init {
        items.postValue(mItems)
    }

    private fun getDataSet(): CopyOnWriteArrayList<Item> {
        val result = CopyOnWriteArrayList<Item>()
        for (i in 1..10) {
            result.add(Item(i, "item $i"))
        }
        return result
    }

    fun updateItem(idx: Int) {
        val newState = mItems[idx].uiState.next()
        val newNum = mItems[idx].uiNum + 1
        mItems[idx].uiState = newState
        mItems[idx].uiNum = newNum
        items.value = mItems
        commands.addCommand(mItems[idx].id) {
            mItems[idx].state = newState
            mItems[idx].num = newNum
            items.postValue(mItems)
            Thread.sleep(1000)
        }
    }

    fun switchCommander() {
        mResettingCommander = !mResettingCommander
        commands.stop()
        commands = if (mResettingCommander) {
            CommandProcessor()
        } else {
            SimpleCommandProcessor()
        }
    }

}