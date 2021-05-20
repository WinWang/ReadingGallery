package com.winwang.mvvm.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.kingja.loadsir.core.LoadSir
import com.tencent.mmkv.MMKV
import com.winwang.mvvm.BuildConfig
import com.winwang.mvvm.R
import com.winwang.mvvm.common.ActivityLifecycleCallbacksAdapter
import com.winwang.mvvm.common.ActivityManager
import com.winwang.mvvm.loadsir.*
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 *Created by WinWang on 2020/6/5
 *Description->
 */
open class BaseApplication
    : Application(), ImageLoaderFactory {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
        initMethod()
    }

    open fun initMethod() {

    }


    private fun init() {
        initArouter()
        registerAppLifecycler()
        initAutoSize()
        initLoadSir()
        initMMKV()
        initLog()
    }


    /**
     * 初始化Arouter
     */
    private fun initArouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this)
    }

    private fun initLog() {
        LogUtils.getConfig().isLogSwitch = BuildConfig.DEBUG
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance()
            .setLog(false)
            .unitsManager
            .setSupportDP(false)
            .setSupportSP(false).supportSubunits = Subunits.PT;
    }

    private fun registerAppLifecycler() {
        registerActivityLifecycleCallbacks(
            ActivityLifecycleCallbacksAdapter(
                onActivityCreated = { activity, savedInstanceState ->
                    ActivityManager.activityMap.add(activity)
                },

                onActivityDestroyed = { activity ->
                    ActivityManager.activityMap.remove(activity)
                }

            )
        )
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .build()
    }


}