package com.winwang.detailmodule

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.winwang.detailmodule.di.detailModule
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */
@AppLifecycle
class DetailApplication : IApplicationLifecycleCallbacks {

    override fun onCreate(context: Context?) {
        initKoin()
    }

    private fun initKoin() {
        try {
            if (GlobalContext.getOrNull() == null) {
                startKoin {
                    modules(detailModule)
                }
            } else {
                loadKoinModules(detailModule)
            }
        } catch (e: Exception) {
            LogUtils.e("KoinDetail${e.toString()}")
        }
        LogUtils.d("初始化Detail工程")
    }

    override fun getPriority() = IApplicationLifecycleCallbacks.NORM_PRIORITY

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }
}