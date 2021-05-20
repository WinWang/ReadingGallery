package com.winwang.homemodule.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.winwang.commonapplib.provider.IHomeProvider
import com.winwang.homemodule.business.ui.fragment.HomeFragment

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */

@Route(path = "/home/main", name = "首页服务")
class HomeProvider : IHomeProvider {

    override fun getHomeFragment() =
        HomeFragment()

    override fun init(context: Context?) {

    }
}