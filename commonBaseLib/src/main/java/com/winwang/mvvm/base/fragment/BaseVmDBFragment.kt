package com.winwang.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
abstract class BaseVmDBFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseDBFragment<DB>() {

    protected val mViewModel: VM by lazy {
        if (isDIViewModel()) {
            val clazz =
                this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
            if (isShareViewModel()) {
                getSharedViewModel<VM>(clazz = clazz)
            } else {
                getViewModel<VM>(clazz = clazz)
            }
        } else {
            val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
            if (isShareViewModel()) {
                ViewModelProvider(
                    requireActivity().viewModelStore,
                    requireActivity().defaultViewModelProviderFactory
                ).get<VM>(types[0] as Class<VM>)
            } else {
                ViewModelProvider(this).get<VM>(types[0] as Class<VM>)
            }
        }
    }

    /**
     * 是否复用顶层Activity的viewmodel来通信
     */
    open fun isShareViewModel(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(mViewModel)
        initDataBindingAndLiveData()
        initView()
        initViewModel()
        // 因为Fragment恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        initStateObserve()
        initObserve()
        if (savedInstanceState == null) {
            initData()
        }
    }

    private fun initDataBindingAndLiveData() {
        mBinding.lifecycleOwner = this
    }

    /**
     * 是否采用依赖注入的方式注入ViewModel
     */
    open fun isDIViewModel(): Boolean = false

    /**
     * 初始化自己的观察者
     */
    abstract fun initObserve()

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

    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
        // Override if need
    }

    /**
     * 普通加载数据
     */
    open fun initData() {
        // Override if need
    }


    /**
     * 通过by lazy来初始化，不在需要单独初始化
     */
    private fun initViewModel() {
//        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }


    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }


}