package com.winwang.detailmodule.http

import com.winwang.detailmodule.model.BookDetailModel
import com.winwang.mvvm.http.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */

interface DetailApi {

    /**
     * 获取详情数据
     */
    @GET("/json/v1/cont/{bookID}.json")
    suspend fun getBookDetail(@Path("bookID") bookID: String): BaseResponse<BookDetailModel>

}