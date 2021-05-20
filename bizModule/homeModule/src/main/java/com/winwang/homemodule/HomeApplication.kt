package com.winwang.homemodule

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks.NORM_PRIORITY
import com.winwang.homemodule.di.homeModule
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import java.lang.Exception

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */
@AppLifecycle
class HomeApplication : IApplicationLifecycleCallbacks {

    override fun onCreate(context: Context?) {
        initKoin()
    }

    private fun initKoin() {
        try {
            if (GlobalContext.getOrNull() == null) {
                startKoin {
                    modules(homeModule)
                }
            } else {
                loadKoinModules(homeModule)
            }

        } catch (e: Exception) {
            LogUtils.e("KoinHome${e.toString()}")
        }
        LogUtils.d("初始化Home工程")
    }

    override fun getPriority() = NORM_PRIORITY

    override fun onTerminate() {
    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }
}