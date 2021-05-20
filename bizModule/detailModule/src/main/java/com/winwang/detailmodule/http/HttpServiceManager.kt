package com.winwang.detailmodule.http

import com.winwang.mvvm.http.RetrofitClient

/**
 *Created by WinWang on 2021/5/17
 *Description->
 */


/**
 * 详情API
 */
object DetailServiceApi :
    DetailApi by RetrofitClient.getRetrofitByUrl(HttpURL.BASE_HOME_URL)
        .create(DetailApi::class.java)