package com.winwang.mvvm.http

class ApiException(var code: Int, override var message: String) : RuntimeException()