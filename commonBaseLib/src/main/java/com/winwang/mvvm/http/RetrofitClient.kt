package com.winwang.mvvm.http

import com.winwang.mvvm.BuildConfig
import com.winwang.mvvm.common.UrlConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(
            HttpLoggingInterceptor(HttpInterceptorLog())
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        )
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(UrlConfig.BASE_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitByUrl(url: String = UrlConfig.BASE_HOST): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()




}