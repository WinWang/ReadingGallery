package com.winwang.homemodule.business.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.homemodule.R
import com.winwang.homemodule.business.ui.viewcomponent.TestEvent
import com.winwang.homemodule.business.ui.viewcomponent.TestViewComponent
import com.winwang.homemodule.databinding.FragmentHomeTestLayoutBinding
import com.winwang.mvvm.base.fragment.BaseVBFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

/**
 *Created by WinWang on 2021/10/11
 *Description->
 */
class TestFragment : BaseVBFragment<FragmentHomeTestLayoutBinding>() {

    val dataList = ArrayList<DataBean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d("执行测试页面》》》》》》》》")
        mTopBar?.setTitle("测试")
        setLoadSir(mBinding.contentLayout)
        for (index in 1..100) {
            val dataBean = DataBean(age = index)
            dataList.add(dataBean)
        }
        val testAdapter = TestAdapter()
        testAdapter.setNewInstance(dataList)
        mBinding.rvTest.layoutManager = LinearLayoutManager(context)
        mBinding.rvTest.adapter = testAdapter
        mBinding.btSendEvent.setOnClickListener {
            EventBus.getDefault().postSticky(TestEvent("123123"))
        }
        mBinding.testShimmerLayout.init()
        mBinding.testShimmerLayout1.init()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            showSuccess()
        }
    }

    override fun loadSirSelf(): Boolean {
        return true
    }

    override fun useLoadSir(): Boolean {
        return true
    }

    override fun useShimmerLayout(): Boolean {
        return true
    }

    override fun shimmerLayout(): Int {
        return R.layout.layout_test_shimmer
    }

    override fun shimmerList(): Boolean {
        return true
    }

    override fun shimmerListSize() = 5


}

class TestAdapter :
    BaseQuickAdapter<DataBean, BaseViewHolder>(layoutResId = R.layout.item_test_layout) {

    override fun convert(holder: BaseViewHolder, item: DataBean) {
        val itemView = holder.itemView
        val lifecycleOwner = recyclerView.findViewTreeLifecycleOwner()
        val viewModelStoreOwner = recyclerView.findViewTreeViewModelStoreOwner()
        lifecycleOwner?.run {
            itemView.setViewTreeLifecycleOwner(this)
        }
        viewModelStoreOwner?.run {
            itemView.setViewTreeViewModelStoreOwner(this)
        }
        val view = holder.getView<TestViewComponent>(R.id.viewTest)
        view.init()
        view.setItem(item)
    }
}

data class DataBean(
    var name: String = "",
    var age: Int
)





