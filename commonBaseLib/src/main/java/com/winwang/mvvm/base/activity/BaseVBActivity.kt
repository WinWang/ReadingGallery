package com.winwang.mvvm.base.activity

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *Created by WinWang on 2020/6/8
 *Description->使用了DataBinding的普通基类
 */
abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {

    lateinit var mBinding: VB

    override fun setContentLayout() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val clazz =
                (if (actualTypeArguments.size > 1) actualTypeArguments[1] else actualTypeArguments[0]) as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            mBinding = method.invoke(null, layoutInflater) as VB
            setContentView(mBinding.root)
        }
    }


    override fun getLayoutId() = 1


}