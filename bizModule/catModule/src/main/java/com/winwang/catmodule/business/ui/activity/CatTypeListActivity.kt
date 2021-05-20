package com.winwang.catmodule.business.ui.activity

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.winwang.catmodule.R
import com.winwang.catmodule.business.adapter.BookTypeListAdapter
import com.winwang.catmodule.databinding.CatModuleActivityCatListLayoutBinding
import com.winwang.catmodule.http.CatApi
import com.winwang.commonapplib.common.RouterConstant
import com.winwang.commonapplib.model.Book
import com.winwang.mvvm.base.activity.BaseVmVBActivity
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2021/5/20
 *Description->分类列表数据
 */
@Route(path = RouterConstant.CATEGORY.CAT_MODULE_BOOK_TYPE_LIST_ROUTE)
class CatTypeListActivity :
    BaseVmVBActivity<CatTypeListViewModel, CatModuleActivityCatListLayoutBinding>(),
    OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Autowired(name = RouterConstant.KEYS.INTENT_DATA)
    @JvmField
    var typeID = ""

    @Autowired(name = RouterConstant.KEYS.INTENT_NAME)
    @JvmField
    var name = ""

    override fun isDIViewModel() = true

    private val dataList = ArrayList<Book>()
    private val mAdapter: BookTypeListAdapter by lazy {
        BookTypeListAdapter(dataList)
    }


    override fun initView() {
        super.initView()
        mTopBar?.setTitle(name)
        mBinding.viewContentLoadsir.setColorSchemeColors(resources.getColor(R.color.colorAccent))
        mBinding.viewContentLoadsir.setOnRefreshListener(this)
        mBinding.rvTypeList.adapter = mAdapter
        mAdapter.setOnItemClickListener(this)

    }

    override fun initObserve() {

    }

    override fun loadNet() {
        super.loadNet()
        mViewModel.getBookTypeList(typeID).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                dataList.clear()
                dataList.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        showToast(dataList[position].name)
    }

    override fun onRefresh() {
        loadNet()
    }

    override fun hideRefresh() {
        super.hideRefresh()
        mBinding.viewContentLoadsir.isRefreshing = false
    }


}


class CatTypeListViewModel(private val api: CatApi) : BaseViewModel() {
    fun getBookTypeList(typeId: String): LiveData<List<Book>> = emit {
        api.getCategoryList(typeId).resultData().books
    }
}
