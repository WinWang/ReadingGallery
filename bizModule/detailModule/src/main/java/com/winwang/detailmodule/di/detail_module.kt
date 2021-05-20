package com.winwang.detailmodule.di


import com.winwang.detailmodule.business.activity.BookDetailViewModel
import com.winwang.detailmodule.http.DetailApi
import com.winwang.detailmodule.http.DetailServiceApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 三者的关系是一层一层依赖，中间的查找在koin里面通过get（）查找获取 -viewmodle依赖Respository --> Respository依赖remoteModule的ApiService
 */


//viewmodel注入管理类
val viewModelModule = module {
    viewModel { BookDetailViewModel() }
}

//远程请求数据类
val remoteModule = module {
    //single 单列注入
    single<DetailApi> { DetailServiceApi }
}

val detailModule = listOf(viewModelModule, remoteModule)

