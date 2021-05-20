package com.winwang.homemodule.http

import com.winwang.homemodule.model.HomeListModel
import com.winwang.mvvm.http.BaseResponse
import retrofit2.http.GET

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */
interface HomeApi {

    /**
     * 最新更新
     */
    @GET("/json/v1/home/update.json")
    suspend fun getHomeUpdate(): BaseResponse<HomeListModel>


    /**
     * 最新更新
     */
    @GET("/json/v1/home/serial.json")
    suspend fun getHomeSeries(): BaseResponse<HomeListModel>


    /**
     * 热门评书
     */
    @GET("/json/v1/home/ping.json")
    suspend fun getHomeHotBook(): BaseResponse<HomeListModel>


    /**
     * 热门有声
     */
    @GET("/json/v1/home/story.json")
    suspend fun getHomeHotVoice(): BaseResponse<HomeListModel>

}