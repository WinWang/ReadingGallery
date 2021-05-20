package com.winwang.mvvm.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.blankj.utilcode.util.LogUtils
import com.winwang.mvvm.base.BaseApplication
import com.winwang.mvvm.base.lifecycle.MyLifecycleObserver
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.ext.showToast
import com.winwang.mvvm.widget.LoadDialog
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/8/25
 *Description->
 */
@Deprecated(message = "不需要单独使用了，通过基类的isDIViewModel来控制注入方式，不使用多个基类")
abstract class BaseDIViewComponent<VM : BaseViewModel> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    FrameLayout(
        context,
        attrs
    ), MyLifecycleObserver {

    private lateinit var loadingDialog: LoadDialog

    init {
        if (getLayoutId() > -1) {
            inflate(context, getLayoutId(), this)
        }
    }

    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner
    open var mContext: Context = context

    protected val mViewModel: VM by lazy {
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
        viewModelStoreOwner!!.getViewModel<VM>(clazz = clazz)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
//        init()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }


    open fun init() {
        //为了方便开发，放弃使用viewTree获取方式-主要是因为koin注入需要lifecycleOwner，通过viewTree获取的不能强转成componentCallback，暂时使用context强行转换
        //升级koin，官方解决了这个bug，重新使用Android官方推荐方式使用
        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
//        lifecycleOwner = getLifeOwner()!!
//        viewModelStoreOwner = getViewModelOwner()!!
        lifecycleOwner.lifecycle.addObserver(this)
        LogUtils.d("viewInit>>>>>>>>>>")

    }

    @Deprecated(message = "通过ViewTreeLifecycleOwner实现")
    fun getLifeOwner(): LifecycleOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as LifecycleOwner
        } else {
            return null
        }
    }

    @Deprecated(message = "通过ViewTreeViewModelOwner实现")
    fun getViewModelOwner(): ViewModelStoreOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as ViewModelStoreOwner
        } else {
            return null
        }
    }


    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d("onCreate>>>>>>>>>>")
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initView()
        initObserve()
        initData()
    }

    override fun onPause(owner: LifecycleOwner) {
        LogUtils.d("onPause>>>>>>>>>>")
    }

    override fun onResume(owner: LifecycleOwner) {
        LogUtils.d("onResume>>>>>>>>>>")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    protected open fun useEventBus(): Boolean = false

    /**
     * 初始化观察者
     */
    open fun initObserve() {

    }


    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
    }

    /**
     * 普通加载数据
     */
    open fun initData() {
    }

    open fun getLayoutId(): Int = -1

    open fun showToast(toastMessage: String) {
        BaseApplication.instance.showToast(
            toastMessage
        )
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


}