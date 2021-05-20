package com.winwang.homemodule.di


import com.winwang.homemodule.business.ui.fragment.HomeItemViewModel
import com.winwang.homemodule.business.ui.fragment.HomeViewModel
import com.winwang.homemodule.http.HomeApi
import com.winwang.homemodule.http.HomeServiceApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 三者的关系是一层一层依赖，中间的查找在koin里面通过get（）查找获取 -viewmodle依赖Respository --> Respository依赖remoteModule的ApiService
 */


//viewmodel注入管理类
val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { HomeItemViewModel(get()) }
}

//远程请求数据类
val remoteModule = module {
    //single 单列注入
    single<HomeApi> { HomeServiceApi }
}

val homeModule = listOf(viewModelModule,remoteModule)

