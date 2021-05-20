package com.winwang.mvvm.base.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *Created by WinWang on 2020/6/8
 *Description->具有viewbinding功能的Fragment的基类
 */

abstract class BaseVBFragment<VB : ViewBinding> : BaseFragment() {
    lateinit var mBinding: VB

    override fun generateView(inflater: LayoutInflater, container: ViewGroup?) {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val clazz =
                (if (actualTypeArguments.size > 1) actualTypeArguments[1] else actualTypeArguments[0]) as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            mBinding = method.invoke(null, layoutInflater) as VB
        }
        mRootView = mBinding.root
    }

    /**
     * 重写不需要子类复写
     */
    override fun getLayoutId() = 1


}