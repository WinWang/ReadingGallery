package com.winwang.mvvm.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.BarUtils

/**
 *Created by WinWang on 2021/5/18
 *Description->
 */
class FakeStatusBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        BarUtils.setStatusBarCustom(this)
    }

}