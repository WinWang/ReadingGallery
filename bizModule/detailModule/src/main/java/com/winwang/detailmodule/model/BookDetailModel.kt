package com.winwang.detailmodule.model

data class BookDetailModel(
    val book_id: String,
    val click: String,
    val count: String,
    val name: String,
    val pic: String,
    val play_data: List<PlayData>,
    val status: String,
    val synopsis: String,
    val teller: String,
    val time: String,
    val type: String,
    val type_id: String
)