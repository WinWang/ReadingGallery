package com.winwang.homemodule

import com.blankj.utilcode.util.FragmentUtils
import com.winwang.homemodule.business.ui.fragment.HomeFragment
import com.winwang.homemodule.databinding.ActivityMainBinding
import com.winwang.mvvm.base.activity.BaseVBActivity

class MainActivity : BaseVBActivity<ActivityMainBinding>() {

    override fun initViewData() {
        super.initViewData()
        val homeFragment = HomeFragment()
        FragmentUtils.add(supportFragmentManager, homeFragment, R.id.frame_home_module)
        FragmentUtils.show(homeFragment)
    }

}