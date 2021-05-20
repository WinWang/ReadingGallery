package com.winwang.catmodule.di


import com.winwang.catmodule.business.ui.activity.CatTypeListViewModel
import com.winwang.catmodule.business.ui.fragment.CatViewModel
import com.winwang.catmodule.http.CatApi
import com.winwang.catmodule.http.CatServiceApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 三者的关系是一层一层依赖，中间的查找在koin里面通过get（）查找获取 -viewmodle依赖Respository --> Respository依赖remoteModule的ApiService
 */


//viewmodel注入管理类
val viewModelModule = module {
    viewModel { CatViewModel(get()) }
    viewModel { CatTypeListViewModel(get()) }
}

//远程请求数据类
val remoteModule = module {
    //single 单列注入
    single<CatApi> { CatServiceApi }
}

val catModule = listOf(viewModelModule, remoteModule)

