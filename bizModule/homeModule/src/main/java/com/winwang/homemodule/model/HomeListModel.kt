package com.winwang.homemodule.model

import com.winwang.commonapplib.model.Book

data class HomeListModel(
    val books: List<Book>,
    val top: List<Book>
)