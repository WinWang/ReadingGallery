package com.winwang.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.blankj.utilcode.util.FragmentUtils
import com.winwang.commonapplib.common.ServiceConstant
import com.winwang.commonapplib.provider.ICatProvider
import com.winwang.commonapplib.provider.IHomeProvider
import com.winwang.main.databinding.ActivityMainModuleBinding
import com.winwang.mvvm.base.activity.BaseVBActivity

class MainActivity : BaseVBActivity<ActivityMainModuleBinding>() {

    @Autowired(name = ServiceConstant.CAT_MODULE_CAT_ROUTE)
    lateinit var catProvider: ICatProvider

    @Autowired(name = ServiceConstant.HOME_MODULE_HOME_ROUTE)
    lateinit var homeProvider: IHomeProvider

    private val arrayList = ArrayList<Fragment>()

    private val TAG = "MainActivity"

    private var curShowFragment: Fragment? = null

    override fun initViewData() {
        val catFragment = catProvider.getCatFragment()
        val catFragment1 = catProvider.getCatFragment()
        val homeFragment = homeProvider.getHomeFragment()
        val testFragment = homeProvider.getTestFragment()
        arrayList.add(homeFragment)
        arrayList.add(catFragment)
        arrayList.add(testFragment)
        arrayList.add(catFragment1)
        initView()
    }

    private fun initView() {
        switch2Fragment(0)
        mBinding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> switch2Fragment(0)
                R.id.navigation_hot -> switch2Fragment(1)
                R.id.navigation_live -> switch2Fragment(2)
                R.id.navigation_mine -> switch2Fragment(3)
            }
            true
        }
    }

    private fun switch2Fragment(index: Int) {
        kotlin.runCatching {
            supportFragmentManager.apply {
                val fragmentTag = "${TAG}_$index"
                val fragment = getCurFragment(index, fragmentTag)
                fragment?.apply {
                    val transaction = beginTransaction()
                    curShowFragment?.takeIf {
                        it != this
                    }?.also { preShowPage ->
                        transaction.hide(preShowPage)
                    }
                    if (isAdded) {
                        transaction.show(this)
                    } else {
                        transaction.add(R.id.frame_home, this, fragmentTag)
                    }
                    curShowFragment = this
                    transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
                    //使用该方法，解决事务异步提交时，快速点击切换tab时获取到的fragment的isAdded不准确，导致fragment重复添加
                    transaction.commitNowAllowingStateLoss()
                }
            }
        }
    }

    /**
     * 获取当前要展示的Fragment
     */
    private fun getCurFragment(position: Int, fragmentTag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(fragmentTag) ?: arrayList[position]
    }
}