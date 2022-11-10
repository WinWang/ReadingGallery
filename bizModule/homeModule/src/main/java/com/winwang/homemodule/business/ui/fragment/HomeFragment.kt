package com.winwang.homemodule.business.ui.fragment

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BarUtils
import com.winwang.homemodule.R
import com.winwang.homemodule.databinding.FragmentHomeLayoutBinding
import com.winwang.mvvm.base.fragment.BaseVmVBFragment
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.common.SimpleFragmentPagerAdapter

/**
 *Created by WinWang on 2021/5/13
 *Description->
 */
class HomeFragment : BaseVmVBFragment<HomeViewModel, FragmentHomeLayoutBinding>() {

    private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()

    override fun isDIViewModel() = true

    override fun initView() {
        mTopBar?.setTitle("首页")
        val stringArray = resources.getStringArray(R.array.tabList)
        fragmentList.add(HomeItemFragment.getInstance(1))
        fragmentList.add(HomeItemFragment.getInstance(2))
        fragmentList.add(HomeItemFragment.getInstance(3))
        fragmentList.add(HomeItemFragment.getInstance(4))
        mBinding.vpHome.offscreenPageLimit = 4
        val simpleFragmentPagerAdapter =
            SimpleFragmentPagerAdapter(childFragmentManager, fragmentList)
        mBinding.vpHome.adapter = simpleFragmentPagerAdapter
        mBinding.tabHome.setViewPager(mBinding.vpHome, stringArray)
    }


    override fun initObserve() {

    }

    override fun useShimmerLayout(): Boolean {
        return true
    }

}


class HomeViewModel : BaseViewModel() {

}
