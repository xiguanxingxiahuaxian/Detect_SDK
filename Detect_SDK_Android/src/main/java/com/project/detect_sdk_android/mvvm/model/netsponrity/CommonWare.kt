package com.neusoft.basecore.mvvm.model.netsponrity

import com.neusoft.basecore.mvvm.api.commonService
import com.project.detect_sdk_android.mvvm.bean.Aa10
import com.project.detect_sdk_android.mvvm.bean.Message
import retrofit2.http.Query

class CommonWare : BaseWare() {

    suspend fun mLoadAa10(): Aa10 {
        return BaseWare.retrofit!!.create(commonService::class.java)?.getAa10()!!.await()
    }

    suspend fun mCheckEqu(key:String, secret:String, packname:String): Message {
        return BaseWare.retrofit!!.create(commonService::class.java)?.checkEqu(
            key,secret,packname)!!.await()
    }
}