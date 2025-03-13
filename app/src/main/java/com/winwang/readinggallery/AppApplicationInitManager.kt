package com.winwang.readinggallery

import android.content.Context
import com.winwang.catmodule.CatApplication
import com.winwang.detailmodule.DetailApplication
import com.winwang.homemodule.HomeApplication
import com.winwang.mvvm.base.IApplication

/**
 *
 * @Desc     -
 * @author   WinWang
 * @date     2025/3/13
 * @version
 */
class AppApplicationInitManager private constructor() {
    private val appInitArray: MutableList<IApplication> = mutableListOf()
    private var hasInitFlag = false

    companion object {
        private var instance: AppApplicationInitManager? = null

        fun getInstance(): AppApplicationInitManager {
            return instance ?: synchronized(this) {
                instance ?: AppApplicationInitManager().also { instance = it }
            }
        }
    }

    init {
        addInitApplication()
    }

    private fun addInitApplication() {
        appInitArray.add(HomeApplication())
        appInitArray.add(CatApplication())
        appInitArray.add(DetailApplication())
    }

    /**
     * 初始化Application
     * @param context
     */
    fun initAppApplication(context: Context) {
        for (application in appInitArray) {
            if (!hasInitFlag) {
                runCatching {
                    application.onCreate(context)
                }
            }
        }
        hasInitFlag = true
    }
}