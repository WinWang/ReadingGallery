package com.winwang.homemodule.business.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.homemodule.R
import com.winwang.homemodule.business.ui.viewcomponent.TestEvent
import com.winwang.homemodule.business.ui.viewcomponent.TestViewComponent
import com.winwang.homemodule.databinding.FragmentHomeTestLayoutBinding
import com.winwang.mvvm.base.fragment.BaseVBFragment
import org.greenrobot.eventbus.EventBus

/**
 *Created by WinWang on 2021/10/11
 *Description->
 */
class TestFragment : BaseVBFragment<FragmentHomeTestLayoutBinding>() {

    val dataList = ArrayList<DataBean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (index in 1..100) {
            val dataBean = DataBean(age = index)
            dataList.add(dataBean)
        }
        val testAdapter = TestAdapter()
        testAdapter.setNewInstance(dataList)
        mBinding.rvTest.layoutManager = LinearLayoutManager(context)
        mBinding.rvTest.adapter = testAdapter
        mBinding.btSendEvent.setOnClickListener {
            EventBus.getDefault().post(TestEvent("123123"))
        }


    }

}

public class TestAdapter :
    BaseQuickAdapter<DataBean, BaseViewHolder>(layoutResId = R.layout.item_test_layout) {

    override fun convert(holder: BaseViewHolder, item: DataBean) {
        val itemView = holder.itemView
        val lifecycleOwner = recyclerView.findViewTreeLifecycleOwner()
        val viewModelStoreOwner = recyclerView.findViewTreeViewModelStoreOwner()
        lifecycleOwner?.run {
            ViewTreeLifecycleOwner.set(itemView, this)
        }
        viewModelStoreOwner?.run {
            ViewTreeViewModelStoreOwner.set(itemView, this)
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





