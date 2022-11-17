package com.winwang.mvvm.loadsir

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kingja.loadsir.callback.Callback
import com.winwang.mvvm.R
import com.winwang.mvvm.loadsir.adapter.DefaultShimmerAdapter

/**
 * Created by WinWang on 2022/11/10
 * Description:默认的骨架占位
 **/
class ShimmerCallback(var shimmerLayout: Int = R.layout.layout_default_item_shimmer_layout, var showList: Boolean = true) : Callback() {


    private val shimmerList = arrayListOf<Int>(0, 1, 2, 3, 4, 5, 7)

    override fun onCreateView(): Int {
        return if (showList) R.layout.layout_shimmer_layout else shimmerLayout
    }

    override fun onViewCreate(context: Context?, view: View?) {
        super.onViewCreate(context, view)
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        //阻断通用设置重试点击按钮
        return false
    }

    override fun onAttach(context: Context?, view: View?) {
        super.onAttach(context, view)
        if (showList) {
            val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_shimmer)
            val defaultShimmerAdapter = DefaultShimmerAdapter(shimmerLayout)
            recyclerView?.recycledViewPool?.setMaxRecycledViews(0, 0)
            defaultShimmerAdapter.setNewInstance(shimmerList)
            context?.let {
                recyclerView?.run {
                    layoutManager = LinearLayoutManager(it)
                    adapter = defaultShimmerAdapter
                }
            }
        }

    }


}