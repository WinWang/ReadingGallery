package com.winwang.catmodule.http

import com.winwang.catmodule.model.BookCategoryModel
import com.winwang.catmodule.model.BookTypeList
import com.winwang.mvvm.http.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by WinWang on 2021/5/19
 *Description->
 */
interface CatApi {

    /**
     * 获取分类信息
     */
    @GET("/json/v1/cat/index.json")
    suspend fun getCategory(): BaseResponse<List<BookCategoryModel>>


    /**
     * 获取分类信息的列表数据
     */
    @GET("/json/v1/cat_list/{typeId}/index/1.json")
    suspend fun getCategoryList(@Path("typeId") typeId: String): BaseResponse<BookTypeList>


}