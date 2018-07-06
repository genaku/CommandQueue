package com.genaku.comandqueue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_layout.view.*
import org.jetbrains.anko.backgroundResource
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by Gena Kuchergin on 04.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class ExampleListAdapter(var items: CopyOnWriteArrayList<Item>, private val listener: (Int) -> Unit)
    : RecyclerView.Adapter<ExampleListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(position, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int =
            items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int, listener: (Int) -> Unit) = with(itemView) {
            val item = items[position]

            tvIcon.backgroundResource = item.state.toColor()
            tvIcon.text = "${item.num}"

            item.uiState.toColor().apply {
                tvTitle.backgroundResource = this
                tvProgress.backgroundResource = this
            }
            tvTitle.text = "${item.title} [${item.uiNum}]"
            tvTitle.setOnClickListener {
                listener(position)
            }

            tvProgress.text = item.progress
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