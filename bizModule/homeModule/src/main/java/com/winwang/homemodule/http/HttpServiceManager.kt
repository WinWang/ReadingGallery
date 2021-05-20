package com.winwang.homemodule.http

import com.winwang.mvvm.http.RetrofitClient

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */


/**
 * 首页API
 */
object HomeServiceApi :
    HomeApi by RetrofitClient.getRetrofitByUrl(HttpURL.BASE_HOME_URL)
        .create(HomeApi::class.java)