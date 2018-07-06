package com.genaku.comandqueue

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by Gena Kuchergin on 04.04.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: ExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = mLayoutManager
        val mAdapter = ExampleListAdapter(CopyOnWriteArrayList()) { it ->
            mViewModel.updateItem(it)
        }

        mViewModel = ViewModelProviders.of(this).get(ExampleViewModel::class.java).apply {
            items.observe(this@MainActivity, Observer { values ->
                values?.apply {
                    mAdapter.items = this
                    mAdapter.notifyDataSetChanged()
                }
            })
        }

        recycler_view.adapter = mAdapter

        switch1.onClick {
            mViewModel.switchCommander()
        }

    }

}