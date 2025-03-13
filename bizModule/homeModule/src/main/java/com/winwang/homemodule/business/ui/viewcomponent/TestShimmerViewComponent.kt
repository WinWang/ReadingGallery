package com.winwang.homemodule.business.ui.viewcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.winwang.homemodule.business.ui.fragment.DataBean
import com.winwang.homemodule.databinding.ViewTestViewcomponentBinding
import com.winwang.mvvm.base.view.BaseVBViewComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by WinWang at 2021/10/11
 * Description->测试ViewComponent版本ShimmerLayout
 */
class TestShimmerViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseVBViewComponent<TestViewViewModel, ViewTestViewcomponentBinding>(context, attrs) {

    var position = 0
    var itemData: DataBean? = null

    override fun useShimmerLayout() = true

    override fun initData() {
        LogUtils.d("我在执行")
    }

    override fun initObserve() {
        super.initObserve()
        lifecycleOwner.run {

        }
    }

    override fun onCreate(source: LifecycleOwner) {
        super.onCreate(source)
        LogUtils.d(">>>>>>>>>>>>初始化")
        lifecycleOwner.lifecycleScope.launch {
            delay(10000)
            showSuccess()
        }
    }

    override fun onDestroy(source: LifecycleOwner) {
        super.onDestroy(source)
        LogUtils.e("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>销毁")
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) {
            LogUtils.d("onWindowVisibilityChanged>>>>可见")
        } else {
            LogUtils.d("onWindowVisibilityChanged>>>>不可见")
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            LogUtils.d("onWindowFocusChanged>>>>可见")
        } else {
            LogUtils.d("onWindowFocusChanged>>>>不可见")
        }
    }

    override fun onWindowSystemUiVisibilityChanged(visible: Int) {
        super.onWindowSystemUiVisibilityChanged(visible)
        if (visible == View.VISIBLE) {
            LogUtils.d("onWindowSystemUiVisibilityChanged>>>>可见")
        } else {
            LogUtils.d("onWindowSystemUiVisibilityChanged>>>>不可见")
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            LogUtils.d("onVisibilityChanged>>>>可见")
        } else {
            LogUtils.d("onVisibilityChanged>>>>不可见")
        }
    }


}


