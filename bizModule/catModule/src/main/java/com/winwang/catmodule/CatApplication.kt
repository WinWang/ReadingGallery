package com.winwang.catmodule

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks.NORM_PRIORITY
import com.winwang.catmodule.di.catModule
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import java.lang.Exception

/**
 *Created by WinWang on 2021/5/18
 *Description->
 */
@AppLifecycle
class CatApplication : IApplicationLifecycleCallbacks {
    override fun onCreate(context: Context?) {
        initKoin()
    }

    private fun initKoin() {
        try {
            if (GlobalContext.getOrNull() == null) {
                startKoin {
                    modules(catModule)
                }
            } else {
                loadKoinModules(catModule)
            }
        } catch (e: Exception) {
            LogUtils.e("KoinCat${e.toString()}")
        }
        LogUtils.d("初始化Cat工程")
    }

    override fun getPriority(): Int = NORM_PRIORITY

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }
}