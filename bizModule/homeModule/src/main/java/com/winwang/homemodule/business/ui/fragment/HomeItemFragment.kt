package com.winwang.homemodule.business.ui.fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import com.winwang.commonapplib.common.RouterConstant
import com.winwang.homemodule.business.adapter.HomeItemAdpater
import com.winwang.homemodule.databinding.FragmentHomeItemLayoutBinding
import com.winwang.homemodule.http.HomeApi
import com.winwang.homemodule.R
import com.winwang.homemodule.model.Book
import com.winwang.homemodule.model.HomeListModel
import com.winwang.mvvm.base.fragment.BaseVmVBFragment
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */
class HomeItemFragment : BaseVmVBFragment<HomeItemViewModel, FragmentHomeItemLayoutBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val LOAD_TYPE = "LOAD_TYPE"
        fun getInstance(type: Int): HomeItemFragment {
            val homeItemFragment = HomeItemFragment()
            val bundle = Bundle()
            bundle.putInt(LOAD_TYPE, type)
            homeItemFragment.arguments = bundle
            return homeItemFragment
        }
    }

    private var dataList = arrayListOf<Book>()

    private lateinit var adapter: HomeItemAdpater

    override fun isDIViewModel() = true

    override fun useLoadSir() = true

    override fun useShimmerLayout() = true

    override fun initView() {
        super.initView()
        mBinding.refreshLayout.setColorSchemeColors(resources.getColor(com.winwang.mvvm.R.color.colorAccent))
        mBinding.refreshLayout.setOnRefreshListener(this)
        adapter = HomeItemAdpater(dataList).apply {
            setGridSpanSizeLookup { gridLayoutManager, viewType, position ->
                val item = dataList[position]
                when (item.itemType) {
                    HomeItemAdpater.HOME_HEADER -> 4
                    HomeItemAdpater.HOME_COVER_VERTICAL -> 4
                    HomeItemAdpater.HOME_COVER_HORIZENTAL -> 1
                    else -> 4
                }
            }

            setOnItemClickListener { adapter, view, position ->
                val book = dataList[position]
                ARouter.getInstance().build(RouterConstant.DETAIL.DETAIL_MODULE_DETAIL_ROUTE)
                    .withString(RouterConstant.KEYS.INTENT_DATA, book.book_id)
                    .withString(RouterConstant.KEYS.INTENT_NAME, book.name)
                    .navigation()
            }

        }
        mBinding.rvHome.adapter = adapter
        mBinding.rvHome.addItemDecoration(
            PinnedHeaderItemDecoration.Builder(HomeItemAdpater.HOME_HEADER)
                .disableHeaderClick(true)
                .create()
        )
    }

    override fun initData() {
        super.initData()

    }


    override fun initObserve() {


    }

    private fun setAdapterData(it: List<Book>) {
        dataList.clear()
        dataList.addAll(it)
        adapter.notifyDataSetChanged()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        loadNet()
    }

    override fun loadNet() {
        super.loadNet()
        val type = arguments?.getInt(LOAD_TYPE, -1)
        type?.run {
            when (this) {
                1 -> {
                    mViewModel.getHomeHotUpdate().observe(viewLifecycleOwner, Observer {
                        setAdapterData(it)
                    })
                }

                2 -> {
                    mViewModel.getHomeSeries().observe(viewLifecycleOwner, Observer {
                        setAdapterData(it)
                    })
                }

                3 -> {
                    mViewModel.getHomeHotBook().observe(viewLifecycleOwner, Observer {
                        setAdapterData(it)
                    })
                }

                4 -> {
                    mViewModel.getHomeHotVoice().observe(viewLifecycleOwner, Observer {
                        setAdapterData(it)
                    })
                }

            }
        }
    }

    override fun hideRefresh() {
        super.hideRefresh()
        mBinding.refreshLayout.isRefreshing = false
    }

    override fun onRefresh() {
        loadNet()
    }


}

class HomeItemViewModel(private val api: HomeApi) : BaseViewModel() {

    /**
     * 最新更新
     */
    fun getHomeHotUpdate(): LiveData<List<Book>> = emit {
        val resultData = api.getHomeUpdate().resultData()
        combineList(resultData)
    }


    /**
     * 最新连载
     */
    fun getHomeSeries(): LiveData<List<Book>> = emit {
        val resultData = api.getHomeSeries().resultData()
        combineList(resultData)
    }

    /**
     * 热门评书
     */
    fun getHomeHotBook(): LiveData<List<Book>> = emit {
        val resultData = api.getHomeHotBook().resultData()
        combineList(resultData)
    }

    /**
     * 热门有声
     */
    fun getHomeHotVoice(): LiveData<List<Book>> = emit {
        val resultData = api.getHomeHotVoice().resultData()
        combineList(resultData)
    }

    private fun combineList(resultData: HomeListModel): ArrayList<Book> {
        val list = ArrayList<Book>()
        val books = resultData.top
        val booksNew = books.mapIndexedNotNull { index, item ->
            item.itemType = HomeItemAdpater.HOME_COVER_HORIZENTAL
            item
        }
        val bookHead1 = Book()
        bookHead1.name = "精品推荐"
        bookHead1.itemType = HomeItemAdpater.HOME_HEADER
        val bookHead2 = Book()
        bookHead2.name = "有声资源"
        bookHead2.itemType = HomeItemAdpater.HOME_HEADER
        list.add(bookHead1)
        list.addAll(booksNew)
        list.add(bookHead2)
        list.addAll(resultData.books)
        return list
    }


}

