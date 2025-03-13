package com.winwang.readinggallery

import com.blankj.utilcode.util.LogUtils
import com.winwang.mvvm.base.BaseApplication

/**
 *Created by WinWang on 2021/5/14
 *Description->
 */
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        AppApplicationInitManager.getInstance().initAppApplication(this.applicationContext)
        LogUtils.d("初始化壳工程")
    }
}