package com.genaku.commandqueue

import java.util.*

/**
 * Created by Gena Kuchergin on 03.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class ResetQueue<K, V> {

    private val mQueue = Collections.synchronizedMap(LinkedHashMap<K, V>())

    fun add(key: K, value: V) {
        mQueue[key] = value
    }

    fun poll(): Map.Entry<K, V>? {
        var firstEntry: Map.Entry<K, V>? = null
        synchronized(mQueue) {
            with(mQueue.iterator()) {
                if (hasNext()) {
                    firstEntry = next()
                    remove()
                }
            }
        }
        return firstEntry
    }

    fun clear() = synchronized(mQueue) {
        mQueue.clear()
    }

}