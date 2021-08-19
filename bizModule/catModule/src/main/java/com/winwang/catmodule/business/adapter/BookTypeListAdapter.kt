package com.winwang.catmodule.business.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.catmodule.R
import com.winwang.catmodule.http.HttpURL.BASE_IMAGE_URL
import com.winwang.homemodule.model.Book

/**
 *Created by WinWang on 2021/5/20
 *Description->
 */
class BookTypeListAdapter(dataList: MutableList<Book>) :
    BaseQuickAdapter<Book, BaseViewHolder>(
        data = dataList,
        layoutResId = R.layout.cat_module_item_home_vertical_layout
    ) {

    override fun convert(holder: BaseViewHolder, item: Book) {
        holder.getView<ImageView>(R.id.iv_cover_home_vertical)
            .load(BASE_IMAGE_URL + item.pic)
        holder.setText(R.id.tv_title_home_vertical, item.name)
        holder.setText(R.id.tv_desc_home_vertical, item.synopsis)
        holder.setText(R.id.tv_voice_home_vertical, item.teller)
        holder.setText(R.id.tv_type_home_vertical, item.type)
        holder.setText(R.id.tv_status_home_vertical, item.status + item.count)
    }
}