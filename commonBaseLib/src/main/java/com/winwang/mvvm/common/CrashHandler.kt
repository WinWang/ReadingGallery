package com.winwang.mvvm.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Looper
import kotlin.system.exitProcess


/**
 *Created by WinWang on 2020/12/28
 *Description->未捕获异常通用处理逻辑
 */
object CrashHandler : Thread.UncaughtExceptionHandler {

    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    fun init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread?, ex: Throwable) {
        exitProcess(0)
    }
}