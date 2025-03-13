package com.winwang.mvvm.base.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.kingja.loadsir.core.LoadSir
import com.winwang.mvvm.R
import com.winwang.mvvm.base.BaseApplication
import com.winwang.mvvm.base.lifecycle.MyLifecycleObserver
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum
import com.winwang.mvvm.ext.showToast
import com.winwang.mvvm.loadsir.*
import com.winwang.mvvm.widget.LoadDialog
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.qualifier
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass


/**
 *Created by WinWang on 2020/8/25
 *Description->具有能感知生命周期和获取viewmodel的控件，如果泛型和Fragment或者Activity里面的Viewmodel相同，
 *             默认获取的就是外层的Fragment或者Activity的Viewmode
 * Tips-------->解决防止内部的viewmodel和外部的viewmodel相同的情况，可以通过koin的Quolifier来处理，在module中
 *              通过name注入不同的viewmode的别名来实现
 */
abstract class BaseVBViewComponent<VM : BaseViewModel, VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    FrameLayout(
        context,
        attrs
    ), MyLifecycleObserver {

    private lateinit var loadingDialog: LoadDialog
    lateinit var mBinding: VB
    private var hasInit = false
    private val mLoadService by lazy {
        val viewComponent = findViewById<View>(R.id.base_component_view)
        val loadSir = LoadSir.Builder()
            .addCallback(TimeoutCallback())
            .addCallback(ShimmerCallback(shimmerLayout(), shimmerList()))
            .addCallback(ErrorCallback())
            .addCallback(CustomCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(if (useShimmerLayout()) ShimmerCallback::class.java else LoadingCallback::class.java)
            .build()
        loadSir.register(viewComponent ?: this) {
            loadNet()
        }
    }

    init {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val clazz = actualTypeArguments[1] as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            mBinding = method.invoke(null, LayoutInflater.from(context)) as VB
        }
        removeAllViews()
        addView(mBinding.root)
    }


    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner
    open var mContext: Context = context


    /**
     * 是否开启骨架屏
     */
    open fun useShimmerLayout(): Boolean = false

    /**
     * 骨架屏是否是列表
     */
    open fun shimmerList(): Boolean = false

    /**
     * 骨架屏布局---shimmerList返回true代表骨架屏的item，shimmerList返回false代表骨架屏view的布局
     */
    open fun shimmerLayout() = R.layout.layout_default_item_shimmer_layout


    protected val mViewModel: VM by lazy {
        if (isDIViewModel()) {
            val clazz =
                this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
            viewModelStoreOwner.getViewModel<VM>(
                qualifier = if (!TextUtils.isEmpty(koinQualifier())) qualifier(koinQualifier()) else null,
                clazz = clazz
            )
        } else {
            val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
            ViewModelProvider(viewModelStoreOwner).get<VM>(types[0] as Class<VM>)
        }
    }

    /**
     * 设置Quolifier别名-在viewcomponent中可有获取自己独立复用的Viewmodel，防止viewmodel中的参数污染
     */
    open fun koinQualifier(): String = ""

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }


    /**
     * 是否采用依赖注入的方式注入ViewModel
     */
    open fun isDIViewModel(): Boolean = false

    open fun init() {
        if (!hasInit) {
            lifecycleOwner = this.findViewTreeLifecycleOwner()!!
            viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
//        lifecycleOwner = getLifeOwner()!!
//        viewModelStoreOwner = getViewModelOwner()!!
            lifecycleOwner.lifecycle.addObserver(this)
            LogUtils.d("viewInit>>>>>>>>>>")
            hasInit = true
        }

    }


    override fun onDestroy(source: LifecycleOwner) {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        source.lifecycle.removeObserver(this)
    }

    override fun onCreate(source: LifecycleOwner) {
        LogUtils.d("onCreate>>>>>>>>>>")
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        if (useShimmerLayout()) {
            findViewById<View>(R.id.base_component_view).visibility = GONE
            mLoadService.loadLayout
        }
        initView()
        initViewModel()
        initStateObserve()
        initObserve()
        initData()
    }

    private fun initStateObserve() {
        if (useShimmerLayout()) {
            mViewModel.viewStatus.observe(lifecycleOwner, Observer {
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
    }

    override fun onPause(source: LifecycleOwner) {
        LogUtils.d("onPause>>>>>>>>>>")
    }

    override fun onResume(source: LifecycleOwner) {
        LogUtils.d("onResume>>>>>>>>>>")
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

    open fun loadNet() {

    }

    open fun getLayoutId(): Int = -1

    private fun initViewModel() {
//        mViewModel = ViewModelProvider(viewModelStoreOwner).get(viewModelClass())
    }

//    abstract fun viewModelClass(): Class<VM>

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

    fun showError() {
        mLoadService?.showCallback(ErrorCallback::class.java)
    }

    fun showSuccess() {
//        mBinding.root.visibility = VISIBLE
        findViewById<View>(R.id.base_component_view).visibility = VISIBLE
        mLoadService?.showSuccess()
    }

    fun showEmpty() {
//        mBinding.root.visibility = VISIBLE
        findViewById<View>(R.id.base_component_view).visibility = VISIBLE
        mLoadService?.showCallback(EmptyCallback::class.java)
    }

    fun showLoading() {
//        mBinding.root.visibility = VISIBLE
        findViewById<View>(R.id.base_component_view).visibility = VISIBLE
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    fun showTimeOut() {
//        mBinding.root.visibility = VISIBLE
        findViewById<View>(R.id.base_component_view).visibility = VISIBLE
        mLoadService?.showCallback(TimeoutCallback::class.java)
    }


}