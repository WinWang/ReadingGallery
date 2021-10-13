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
 * Description->
 */
class TestViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseVBViewComponent<TestViewViewModel, ViewTestViewcomponentBinding>(context, attrs) {

    var position = 0
    var itemData: DataBean? = null

    override fun initData() {
        LogUtils.d("我在执行")
    }

    override fun initObserve() {
        super.initObserve()
        lifecycleOwner.run {

        }
    }

    fun setAdapterPosition(position: Int) {
        this.position = position
        mBinding.tvTest.text = position.toString()
    }

    fun setItem(data: DataBean) {
        itemData = data
        mBinding.tvTest.text = data.age.toString()
    }

    override fun onCreate(source: LifecycleOwner) {
        super.onCreate(source)
        LogUtils.d(">>>>>>>>>>>>初始化")
    }

    override fun onDestroy(source: LifecycleOwner) {
        super.onDestroy(source)
        LogUtils.e("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>销毁")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtils.d(">>>>>>>>>>>>绑定视窗")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        LogUtils.e(">>>>>>>>>>>>解绑视窗")
    }

    override fun useEventBus() = true

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun eventFun(event: TestEvent) {
        itemData?.run {
            this.age = event.name.toInt()
        }
        LogUtils.i("我在接收事件${event.name}>>>>>>>>>列表位置${position}")
        mBinding.tvTest.text = event.name + ">>>>>>>>>>" + itemData?.age
    }

}


class TestViewViewModel : BaseViewModel() {

}

data class TestEvent(
    val name: String = ""
)
