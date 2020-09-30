package com.neusoft.basecore.mvvm.model.netsponrity

import android.util.Log
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import com.project.detect_sdk_android.mvvm.config.AppConfig
import com.project.detect_sdk_android.mvvm.config.Sslsocketclient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseWare {

    object BaseWare {

        val retrofit by lazy {
            Retrofit.Builder()
                .client(OkHttpClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(AppConfig.BASEURL)
                .build();
        }

        fun OkHttpClient(): OkHttpClient? {
            //开发环境中，打印日志 发布版不在打印
            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor =
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String?) {
                        /*Logger().info()*/
                        Log.i("OkHttpClient", "OkHttpMessage:$message")
                    }

                })
            loggingInterceptor.setLevel(level)
            return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .sslSocketFactory(Sslsocketclient.sSLSocketFactory)
                .hostnameVerifier(Sslsocketclient.hostnameVerifier)
                .build()
        }
    }
}