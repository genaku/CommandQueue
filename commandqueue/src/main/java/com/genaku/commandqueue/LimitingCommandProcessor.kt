package com.genaku.commandqueue

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Gena Kuchergin on 08.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LimitingCommandProcessor<in K>(private val timeThreshold: Long) : CommandProcessor<K>() {

    private val mCommandsLastMillis = ConcurrentHashMap<K, Long>()

    override fun addCommand(key: K, command: () -> Unit) {
        // start not too often
        val commandLastMillis = getCommandLastMillis(key)
        val currentMillis = System.currentTimeMillis()
        if (currentMillis - commandLastMillis > timeThreshold) {
            mCommandsLastMillis[key] = currentMillis
            super.addCommand(key, command)
        }
    }

    private fun getCommandLastMillis(key: K): Long =
            mCommandsLastMillis[key] ?: 0

}