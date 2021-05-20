package com.winwang.mvvm.base.dialog

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/6/16
 *Description->Viewmodel+Databinding的Dialog
 */
abstract class BaseVmDBDialog<VM : BaseViewModel, DB : ViewDataBinding> : BaseDBDialog<DB>() {

    protected val mViewModel: VM by lazy {
        if (isDIViewModel()) {
            val clazz =
                this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
            getViewModel<VM>(clazz = clazz)
        } else {
            val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
            ViewModelProvider(this).get<VM>(types[0] as Class<VM>)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(mViewModel)
        initDataBindingAndLiveData()
        initView()
        initViewModel()
        initObserve()
        initStateObserve()
        if (savedInstanceState == null) {
            initData()
        }
    }

    private fun initDataBindingAndLiveData() {
        mBinding.lifecycleOwner = this
    }


    /**
     * 初始化自己的观察者
     */
    abstract fun initObserve()

    /**
     * 普通加载数据
     */
    open fun initData() {
        // Override if need
    }

    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
        // Override if need
    }

    /**
     * 是否采用依赖注入的方式注入ViewModel
     */
    open fun isDIViewModel(): Boolean = false

    open fun initStateObserve() {
        mViewModel.viewStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                ViewStatusEnum.SUCCESS -> {
                    showSuccess()
                }

                ViewStatusEnum.ERROR -> {
                    showError()
                }

                ViewStatusEnum.EMPTY -> {
                    showEmpty()
                }

                ViewStatusEnum.NETWORKERROR -> {
                    showTimeOut()
                }

            }
        })
    }


    private fun initViewModel() {
        //通过lazy初始化
//        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(mViewModel)
    }

}