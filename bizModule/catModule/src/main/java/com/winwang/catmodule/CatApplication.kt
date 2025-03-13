package com.winwang.catmodule

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.winwang.catmodule.di.catModule
import com.winwang.mvvm.base.IApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2021/5/18
 *Description->
 */
class CatApplication : IApplication {
    override fun onCreate(context: Context) {
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
}