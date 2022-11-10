package com.winwang.mvvm.base.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.qmuiteam.qmui.widget.QMUITopBar
import com.winwang.mvvm.R
import com.winwang.mvvm.base.IView
import com.winwang.mvvm.base.view.BaseViewComponent
import com.winwang.mvvm.loadsir.*
import com.winwang.mvvm.widget.LoadDialog
import org.greenrobot.eventbus.EventBus

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */

abstract class BaseFragment : Fragment(), IView {

    private var mLoadService: LoadService<Any>? = null
    private lateinit var loadingDialog: LoadDialog
    open var mContext: FragmentActivity? = null
    open var mTopBar: QMUITopBar? = null
    private var lazyLoaded = false

    lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        generateView(inflater, container)
        /*******处理是否使用loadSir逻辑 */
        initTitleBar(mRootView)
        mContext = requireActivity()
        return if (useLoadSir() && !loadSirSelf()) {
            setLoadSir(mRootView)
            mLoadService?.loadLayout
        } else {
            mRootView
        }
    }


    open fun generateView(inflater: LayoutInflater, container: ViewGroup?) {
        mRootView = inflater.inflate(getLayoutId(), container, false)
    }

    private fun initTitleBar(mRootView: View?) {
        mTopBar = mRootView?.findViewById(R.id.qm_topbar)
        val fakeStatusBar = mRootView?.findViewById<View>(R.id.fake_status_bar)
        fakeStatusBar?.run {
            BarUtils.setStatusBarCustom(this)
        }
        mTopBar?.run {
            if (isShowBack()) {
                addLeftBackImageButton()
            }
//            BarUtils.addMarginTopEqualStatusBarHeight(this)
        }
    }


    fun initViewComponent() {
        mRootView?.run {
            if (this is ViewGroup) {
                var vp: ViewGroup = mRootView as ViewGroup
                (0..vp.childCount).forEachIndexed { index, item ->
                    val childAt = this.getChildAt(index)
                    if (childAt is BaseViewComponent<*>) {
                        val baseViewComponent = childAt as BaseViewComponent<*>
                        baseViewComponent.init()
                    }
                }
            }
        }
    }


    /**
     * 是否展示返回按钮
     */
    open fun isShowBack(): Boolean = false

    open fun useShimmerLayout(): Boolean = false

    open fun shimmerList(): Boolean = true

    open fun shimmerLayout() = R.layout.layout_default_item_shimmer_layout

    protected open fun useEventBus(): Boolean = false

    abstract fun getLayoutId(): Int

    open fun setLoadSir(it: View) {
        mLoadService = LoadSir.getDefault().register(it) {
            mLoadService?.showCallback(LoadingCallback::class.java)
            loadNet()
        }
        if (useShimmerLayout()) {
            mLoadService?.loadLayout?.setupCallback(ShimmerCallback(shimmerLayout(), shimmerList()))

            mLoadService?.showCallback(ShimmerCallback::class.java)
        }
    }

    //自己设置loadSir布局
    protected open fun loadSirSelf(): Boolean = false

    //使用loadSir
    protected open fun useLoadSir(): Boolean = false

    open fun loadNet() {

    }

    /**
     * 懒加载数据
     */
    open fun lazyLoadData() {
        // Override if need
    }

    override fun onResume() {
        super.onResume()
        if (!lazyLoaded) {
            lazyLoadData()
            lazyLoaded = true
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViewComponent()
    }

    override fun onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }


    fun showError() {
        mLoadService?.showCallback(ErrorCallback::class.java)
        hideRefresh()
    }

    fun showSuccess() {
        mLoadService?.showSuccess()
        hideRefresh()
    }

    fun showEmpty() {
        mLoadService?.showCallback(EmptyCallback::class.java)
        hideRefresh()
    }

    fun showTimeOut() {
        mLoadService?.showCallback(TimeoutCallback::class.java)
        hideRefresh()
    }

    fun showLoading() {
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    fun showToast(toastMsg: String) {
        ToastUtils.showShort(toastMsg)
    }

    fun showDialogLoading(loadingString: String? = "") {
        if (!this::loadingDialog.isInitialized) {
            context?.run {
                loadingDialog = LoadDialog(this)
            }
        }
        this.loadingDialog.showLoading(loadingString)
    }

    fun hideLoading() {
        if (this::loadingDialog.isInitialized) {
            loadingDialog.hideLoading()
        }
    }

    override fun hideRefresh() {

    }


}