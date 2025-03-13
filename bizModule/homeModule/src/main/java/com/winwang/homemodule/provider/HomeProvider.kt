package com.winwang.homemodule.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.winwang.commonapplib.common.ServiceConstant
import com.winwang.commonapplib.provider.IHomeProvider
import com.winwang.homemodule.business.ui.fragment.HomeFragment
import com.winwang.homemodule.business.ui.fragment.TestFragment

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */

@Route(path = ServiceConstant.HOME_MODULE_HOME_ROUTE, name = "首页服务")
class HomeProvider : IHomeProvider {

    override fun getHomeFragment() =
        HomeFragment()

    override fun getTestFragment() =
        TestFragment()

    override fun getPagingFragment(): Fragment {
        TODO("Not yet implemented")
    }


    override fun init(context: Context?) {

    }
}