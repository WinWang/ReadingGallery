package com.winwang.catmodule.business.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.catmodule.R
import com.winwang.catmodule.model.BookCategoryModel
import com.winwang.catmodule.model.BookType

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */
class BookCatAdapter(data: MutableList<BookType>) :
    BaseMultiItemQuickAdapter<BookType, BaseViewHolder>(data = data) {

    companion object {
        const val CAT_CONTENT = 0
        const val CAT_HEADER = 1
    }

    init {
        addItemType(CAT_HEADER, R.layout.cat_module_item_cat_section_layout)
        addItemType(CAT_CONTENT, R.layout.cat_module_item_cat_content_layout)
    }


    override fun convert(holder: BaseViewHolder, item: BookType) {
        when (item.itemType) {
            CAT_CONTENT -> {
                holder.setText(R.id.qmbt_content_title, item.name)
            }

            CAT_HEADER -> {
                holder.setText(R.id.tv_title_home_section, item.name)
            }

        }
    }
}