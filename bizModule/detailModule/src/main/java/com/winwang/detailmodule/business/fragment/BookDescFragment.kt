package com.winwang.detailmodule.business.fragment

import androidx.lifecycle.Observer
import com.winwang.detailmodule.business.activity.BookDetailViewModel
import com.winwang.detailmodule.databinding.BookDetailFragmentBookDescLayoutBinding
import com.winwang.mvvm.base.fragment.BaseVmVBFragment

/**
 *Created by WinWang on 2021/5/21
 *Description->简介Fragment
 */
class BookDescFragment :
    BaseVmVBFragment<BookDetailViewModel, BookDetailFragmentBookDescLayoutBinding>() {

    override fun isDIViewModel() = true

    override fun isShareViewModel() = true

    override fun useLoadSir() = true

    override fun initView() {
        super.initView()
    }

    override fun initObserve() {
        mViewModel.playListLiveData.observe(this, Observer {
            mBinding.tvBookDesc.text = it.synopsis
        })
    }
}
