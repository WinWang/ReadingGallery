package com.winwang.mvvm.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.winwang.mvvm.base.IView

/**
 *Created by WinWang on 2020/6/8
 *Description->使用了DataBinding的普通基类
 */
abstract class BaseDBActivity<DB : ViewDataBinding> : BaseActivity() {

    lateinit var mDataBind: DB

    override fun setContentLayout() {
        mDataBind = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onDestroy() {
        super.onDestroy()
        //判断延迟属性是否初始化了
        if (::mDataBind.isInitialized) {
            mDataBind.unbind()
        }
    }


}