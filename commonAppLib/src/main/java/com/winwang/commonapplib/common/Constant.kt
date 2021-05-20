package com.winwang.commonapplib.common

/**
 *Created by WinWang on 2021/5/18
 *Description->
 */

object RouterConstant {

    /**
     * 首页模块
     */
    object HOME {

    }

    /**
     * 分类模块
     */
    object CATEGORY {
        const val CAT_MODULE_BOOK_TYPE_LIST_ROUTE = "/cat/booklist"
    }

    /**
     * 详情模块
     */
    object DETAIL {
        const val DETAIL_MODULE_DETAIL_ROUTE = "/detail/main"
    }


    /**
     * 传参key
     */
    object KEYS {
        const val INTENT_DATA = "intent_data"
        const val INTENT_NAME = "intent_NAME"
    }


}


/**
 *Created by WinWang on 2021/5/18
 *Description->
 */
object ServiceConstant {
    const val HOME_MODULE_HOME_ROUTE = "/home/main"
    const val CAT_MODULE_CAT_ROUTE = "/cat/main"
}