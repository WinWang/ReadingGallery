package com.winwang.mvvm.base.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *Created by WinWang on 2020/6/16
 *Description->通用的Dialog
 */
abstract class BaseVBDialog<VB : ViewBinding> : BaseDialog() {

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