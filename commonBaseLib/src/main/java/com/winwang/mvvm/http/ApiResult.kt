package com.winwang.mvvm.http

data class BaseResponse<T>(val code: Int, val status: String, private val data: T) {
    fun resultData(): T {
        if (code == 200) {
            return data
        } else {
            throw ApiException(code, status)
        }
    }
}
