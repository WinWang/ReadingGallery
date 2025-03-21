package com.winwang.commonapplib.provider

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */
interface IHomeProvider : IProvider {

    fun getHomeFragment(): Fragment

    /**
     * 获取测试Fragment
     */
    fun getTestFragment(): Fragment


    /**
     * 获取Paging测试Fragment
     */
    fun getPagingFragment(): Fragment

}