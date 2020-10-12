package com.neusoft.basecore.mvvm.api


import com.project.detect_sdk_android.mvvm.bean.Aa10
import com.project.detect_sdk_android.mvvm.bean.EquTable
import com.project.detect_sdk_android.mvvm.bean.Message
import com.project.detect_sdk_android.mvvm.config.AppConfig
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface commonService {
    /*@GET(AppConfig.AA10)*/
    fun getAa10(): Deferred<Aa10>

    @GET(AppConfig.CHECKEQU)
    fun checkEqu(
        @Query("key") key: String,
        @Query("secret") secret: String,
        @Query("packname") packname: String
    ): Deferred<Message>


    @POST(AppConfig.ONSTART)
    fun updateStart(@Body equTable: EquTable): Deferred<Message>

    @POST(AppConfig.ONEND)
    fun updateEnd(@Body equTable: EquTable): Deferred<Message>
}