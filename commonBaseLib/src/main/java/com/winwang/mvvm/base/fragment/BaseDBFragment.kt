package com.winwang.mvvm.base.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *Created by WinWang on 2020/6/8
 *Description->具有databinding功能的Fragment的基类
 */

abstract class BaseDBFragment<DB : ViewDataBinding> : BaseFragment() {

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