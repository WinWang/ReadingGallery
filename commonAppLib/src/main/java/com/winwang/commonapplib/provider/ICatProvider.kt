package com.winwang.commonapplib.provider

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */
interface ICatProvider : IProvider {

    fun getCatFragment(): Fragment

}