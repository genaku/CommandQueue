package com.genaku.comandqueue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_layout.view.*
import org.jetbrains.anko.backgroundResource
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by Gena Kuchergin on 04.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class CustomAdapter(var items: CopyOnWriteArrayList<Item>, private val listener: (Int) -> Unit)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(position, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int =
            items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: FrameLayout = itemView.findViewById(R.id.icon)
        val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(position: Int, listener: (Int) -> Unit) = with(itemView) {
            val item = items[position]
            icon.backgroundResource = item.state.toColor()
            textView.backgroundResource = item.uiState.toColor()
            textView.text = "${item.title} [${item.uiNum}]"
            textView2.text = "${item.num}"
            textView.setOnClickListener {
                listener(position)
            }
        }

        private fun ItemState.toColor(): Int {
            return when (this) {
                ItemState.WHITE -> android.R.color.white
                ItemState.GREEN -> android.R.color.holo_green_light
                ItemState.RED -> android.R.color.holo_red_light
                ItemState.BLUE -> android.R.color.holo_blue_light
            }
        }
    }

}