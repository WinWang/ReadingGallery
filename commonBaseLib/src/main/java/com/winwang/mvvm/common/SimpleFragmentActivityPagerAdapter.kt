package com.winwang.mvvm.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

open class SimpleFragmentActivityPagerAdapter(
    fragment: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

//    override fun getItem(position: Int): Fragment = fragments[position]
//
//    override fun getCount(): Int = fragments.size
//
//    override fun getPageTitle(position: Int): CharSequence? = titles?.get(position)
//
//    /**
//     * 默认为位置，子类需要覆盖默认，才能保证对应位置的fragment可以改变成其他的fragment，
//     * 不然同一位置一直都是最初添加的那个fragment，其他的添加不进去
//     * {@link FragmentStatePagerAdapter#getItem(int)}
//     */
//    override fun getItemId(position: Int): Long {
//        return fragments[position].hashCode().toLong()
//    }
}