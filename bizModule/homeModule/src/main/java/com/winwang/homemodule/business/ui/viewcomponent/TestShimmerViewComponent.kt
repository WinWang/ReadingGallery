package com.winwang.homemodule.business.ui.viewcomponent

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.LogUtils
import com.winwang.homemodule.business.ui.fragment.DataBean
import com.winwang.homemodule.databinding.ViewTestViewcomponentBinding
import com.winwang.mvvm.base.view.BaseVBViewComponent
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
    }

    override fun onDestroy(source: LifecycleOwner) {
        super.onDestroy(source)
        LogUtils.e("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>销毁")
    }

}


