package com.winwang.detailmodule.business.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.winwang.commonapplib.common.RouterConstant
import com.winwang.detailmodule.databinding.BookDetailActivityBookBinding
import com.winwang.mvvm.base.activity.BaseVmVBActivity
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */
@Route(path = RouterConstant.DETAIL.DETAIL_MODULE_DETAIL_ROUTE)
class BookDetailActivity : BaseVmVBActivity<BookDetailViewModel, BookDetailActivityBookBinding>() {

    override fun initView() {
        super.initView()
        mTopBar?.setTitle("详情页面")
    }

    override fun initObserve() {

    }
}

class BookDetailViewModel : BaseViewModel() {

}