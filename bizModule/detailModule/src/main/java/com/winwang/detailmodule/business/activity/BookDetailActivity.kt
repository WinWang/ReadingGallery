package com.winwang.detailmodule.business.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.winwang.commonapplib.common.RouterConstant
import com.winwang.detailmodule.R
import com.winwang.detailmodule.business.fragment.BookDescFragment
import com.winwang.detailmodule.business.fragment.PlayListFragment
import com.winwang.detailmodule.databinding.BookDetailActivityBookBinding
import com.winwang.detailmodule.http.DetailApi
import com.winwang.detailmodule.model.BookDetailModel
import com.winwang.mvvm.base.activity.BaseVmVBActivity
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.common.SimpleFragmentPagerAdapter
import org.koin.android.ext.android.inject

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */
@Route(path = RouterConstant.DETAIL.DETAIL_MODULE_DETAIL_ROUTE)
class BookDetailActivity : BaseVmVBActivity<BookDetailViewModel, BookDetailActivityBookBinding>() {


    @Autowired(name = RouterConstant.KEYS.INTENT_DATA)
    @JvmField
    var bookId: String = ""

    @Autowired(name = RouterConstant.KEYS.INTENT_NAME)
    @JvmField
    var bookName: String = ""


    override fun isDIViewModel() = true

    private val bookFragment by inject<BookDescFragment>()
    private val playFragment by inject<PlayListFragment>()
    private val fragmentList = ArrayList<Fragment>()

    override fun initView() {
        super.initView()
        mTopBar?.setTitle(bookName)
        mViewModel.bookId = bookId
        val stringArray = resources.getStringArray(R.array.tab_list)
        fragmentList.add(playFragment)
        fragmentList.add(bookFragment)
        val simpleFragmentPagerAdapter =
            SimpleFragmentPagerAdapter(supportFragmentManager, fragmentList)
        mBinding.vpDetail.adapter = simpleFragmentPagerAdapter
        mBinding.tabDetail.setViewPager(mBinding.vpDetail, stringArray)
    }

    override fun initObserve() {

    }
}

class BookDetailViewModel(private val api: DetailApi) : BaseViewModel() {

    var bookId: String = ""

    val playListLiveData by lazy {
        MutableLiveData<BookDetailModel>()
    }

    fun getBookPlayList(): LiveData<BookDetailModel> = emit {
        api.getBookDetail(bookId).resultData()
    }

    fun getPlayList() {
        launch {
            val resultData = api.getBookDetail(bookId).resultData()
            playListLiveData.postValue(resultData)
        }
    }


}