package com.genaku.commandqueue

import org.jetbrains.anko.doAsync
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Gena Kuchergin on 03.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
open class CommandProcessor<in K> {

    private var mQueueProcessing = AtomicBoolean(false)
    private val mQueue = ResetQueue<K, () -> Unit>()

    open fun addCommand(key: K, command: () -> Unit) {
        mQueue.add(key, command)
        startProcessQueue()
    }

    open fun stop() =
            mQueue.clear()

    protected fun startProcessQueue() {
        if (!mQueueProcessing.get()) {
            doAsync {
                processQueue()
            }
        }
    }

    private fun processQueue() {
        mQueueProcessing.set(true)
        var nextCommand = getNextCommand()
        while (nextCommand != null) {
            try {
                nextCommand()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            nextCommand = getNextCommand()
        }
        mQueueProcessing.set(false)
    }

    protected open fun getNextCommand(): (() -> Unit)? =
            mQueue.poll()?.value

}