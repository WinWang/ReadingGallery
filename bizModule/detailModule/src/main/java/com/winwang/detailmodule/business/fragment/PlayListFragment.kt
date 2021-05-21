package com.winwang.detailmodule.business.fragment

import androidx.lifecycle.Observer
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.detailmodule.R
import com.winwang.detailmodule.business.activity.BookDetailViewModel
import com.winwang.detailmodule.databinding.BookDetailFragmentPlayListLayoutBinding
import com.winwang.detailmodule.http.HttpURL.BASE_IMAGE_URL
import com.winwang.detailmodule.model.PlayData
import com.winwang.mvvm.base.fragment.BaseVmVBFragment

/**
 *Created by WinWang on 2021/5/21
 *Description->
 */
class PlayListFragment :
    BaseVmVBFragment<BookDetailViewModel, BookDetailFragmentPlayListLayoutBinding>() {

    /**
     * 开启复用Activity中的ViewModel
     */
    override fun isShareViewModel() = true

    override fun isDIViewModel() = true

    override fun useLoadSir() = true

    private val dataList = ArrayList<PlayData>()

    private val mAdapter by lazy {
        PlayListAdapter(dataList)
    }

    override fun initView() {
        super.initView()
        mBinding.rvDetail.adapter = mAdapter
    }

    override fun initData() {
        super.initData()

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        loadNet()
    }

    override fun initObserve() {
        mViewModel.playListLiveData.observe(this, Observer {
            if (!it.play_data.isNullOrEmpty()) {
                dataList.clear()
                dataList.addAll(it.play_data)
                mAdapter.notifyDataSetChanged()
            }
            mBinding.ivCoverBook.load(BASE_IMAGE_URL + it.pic)
            mBinding.tvName.text = it.name
            mBinding.tvTeller.text = it.teller
            mBinding.tvType.text = it.type
            mBinding.tvStatus.text = it.status
            mBinding.tvTime.text = it.time
        })
    }

    override fun loadNet() {
        super.loadNet()
        mViewModel.getPlayList()
    }
}


class PlayListAdapter(dataList: MutableList<PlayData>) :
    BaseQuickAdapter<PlayData, BaseViewHolder>(
        data = dataList,
        layoutResId = R.layout.book_detail_item_play_list_layout
    ) {
    override fun convert(holder: BaseViewHolder, item: PlayData) {
        holder.setText(R.id.tv_play_list_title, item.name)
    }

}

