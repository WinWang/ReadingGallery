package com.winwang.homemodule

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.winwang.homemodule.di.homeModule
import com.winwang.mvvm.base.IApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */
class HomeApplication : IApplication {

    override fun onCreate(context: Context) {
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
}