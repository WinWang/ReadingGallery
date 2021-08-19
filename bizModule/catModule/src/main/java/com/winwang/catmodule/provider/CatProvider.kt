package com.winwang.catmodule.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.winwang.catmodule.business.ui.fragment.CatFragment
import com.winwang.commonapplib.common.ServiceConstant
import com.winwang.commonapplib.provider.ICatProvider

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */
@Route(path = ServiceConstant.CAT_MODULE_CAT_ROUTE, name = "分类服务")
class CatProvider : ICatProvider {

    override fun getCatFragment() = CatFragment()

    override fun init(context: Context?) {

    }
}