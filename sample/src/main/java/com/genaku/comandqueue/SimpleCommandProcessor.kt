package com.genaku.comandqueue

import com.genaku.commandqueue.CommandProcessor
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by Gena Kuchergin on 06.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class SimpleCommandProcessor<in K> : CommandProcessor<K>() {

    private val mQueue = ConcurrentLinkedQueue<() -> Unit>()

    override fun addCommand(key: K, command: () -> Unit) {
        mQueue.add(command)
        startProcessQueue()
    }

    override fun stop() =
            mQueue.clear()

    override fun getNextCommand(): (() -> Unit)? =
            mQueue.poll()

}

