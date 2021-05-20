package com.winwang.catmodule.model

import com.chad.library.adapter.base.entity.MultiItemEntity

data class BookType(
    val name: String,
    val type_id: String
) : MultiItemEntity {
    override var itemType = 0
}