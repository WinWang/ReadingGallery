package com.winwang.homemodule.business.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.homemodule.R
import com.winwang.homemodule.http.HttpURL.BASE_IMAGE_URL
import com.winwang.homemodule.model.Book

/**
 *Created by WinWang on 2021/5/18
 *Description->
 */
class HomeItemAdpater(data: MutableList<Book>) :
    BaseMultiItemQuickAdapter<Book, BaseViewHolder>(data) {

    companion object {
        const val HOME_COVER_VERTICAL = 0 //竖直布局
        const val HOME_COVER_HORIZENTAL = 1 //横向布局
        const val HOME_HEADER = 2
    }

    init {
        addItemType(HOME_HEADER, R.layout.home_module_item_home_section_layout)
        addItemType(HOME_COVER_HORIZENTAL, R.layout.home_module_item_home_horizental_layout)
        addItemType(HOME_COVER_VERTICAL, R.layout.home_module_item_home_vertical_layout)
    }

    override fun convert(holder: BaseViewHolder, item: Book) {
        when (item.itemType) {
            HOME_COVER_VERTICAL -> {
                holder.getView<ImageView>(R.id.iv_cover_home_vertical)
                    .load(BASE_IMAGE_URL + item.pic)
                holder.setText(R.id.tv_title_home_vertical, item.name)
                holder.setText(R.id.tv_desc_home_vertical, item.synopsis)
                holder.setText(R.id.tv_voice_home_vertical, item.teller)
                holder.setText(R.id.tv_type_home_vertical, item.type)
                holder.setText(R.id.tv_status_home_vertical, item.status + item.count)
            }

            HOME_COVER_HORIZENTAL -> {
                holder.getView<ImageView>(R.id.iv_cover_home_horizental)
                    .load(BASE_IMAGE_URL + item.pic)
                holder.setText(R.id.tv_title_home_horizental, item.name)
                holder.setText(R.id.tv_status_home_horizental, item.status + item.count)
            }

            HOME_HEADER -> {
                holder.setText(R.id.tv_title_home_section, item.name)
            }
        }
    }
}