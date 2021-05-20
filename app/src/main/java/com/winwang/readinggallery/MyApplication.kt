package com.winwang.readinggallery

import com.blankj.utilcode.util.LogUtils
import com.hm.lifecycle.api.ApplicationLifecycleManager
import com.winwang.mvvm.base.BaseApplication

/**
 *Created by WinWang on 2021/5/14
 *Description->
 */
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ApplicationLifecycleManager.init()
        ApplicationLifecycleManager.onCreate(this)
        LogUtils.d("初始化壳工程")
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationLifecycleManager.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationLifecycleManager.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ApplicationLifecycleManager.onTrimMemory(level)
    }


}