package com.winwang.main

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.blankj.utilcode.util.FragmentUtils
import com.winwang.commonapplib.provider.ICatProvider
import com.winwang.commonapplib.provider.IHomeProvider
import com.winwang.main.databinding.ActivityMainModuleBinding
import com.winwang.mvvm.base.activity.BaseVBActivity

class MainActivity : BaseVBActivity<ActivityMainModuleBinding>() {

    @Autowired(name = "/cat/main")
    @JvmField
    var catProvider: ICatProvider? = null

    @Autowired(name = "/home/main")
    @JvmField
    var homeProvider: IHomeProvider? = null

    val arrayList = ArrayList<Fragment>()

    override fun initViewData() {
        val catFragment = catProvider?.getCatFragment()
        val catFragment1 = catProvider?.getCatFragment()
        val homeFragment = homeProvider?.getHomeFragment()
        val homeFragment1 = homeProvider?.getHomeFragment()
        homeFragment?.let {
            arrayList.add(it)
        }
        catFragment?.let {
            arrayList.add(it)
        }
        homeFragment1?.let {
            arrayList.add(it)
        }
        catFragment1?.let {
            arrayList.add(it)
        }
        initView()
    }

    private fun initView() {
        FragmentUtils.add(supportFragmentManager, arrayList, R.id.frame_home, 0)
        mBinding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> FragmentUtils.showHide(0, arrayList)
                R.id.navigation_hot -> FragmentUtils.showHide(1, arrayList)
                R.id.navigation_live -> FragmentUtils.showHide(2, arrayList)
                R.id.navigation_mine -> FragmentUtils.showHide(3, arrayList)
            }
            true
        }
    }


}