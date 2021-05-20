package com.winwang.mvvm.base.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *Created by WinWang on 2020/6/16
 *Description->使用Databing的Dialog基类
 */
abstract class BaseDBDialog<DB : ViewDataBinding> : BaseDialog() {

    lateinit var mBinding: DB

    override fun generateView(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //判断延迟属性是否初始化了
        if (::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }

}