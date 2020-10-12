package com.neusoft.basecore.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.neusoft.basecore.mvvm.model.netsponrity.CommonWare
import com.project.detect_sdk_android.mvvm.bean.Aa10
import com.project.detect_sdk_android.mvvm.bean.EquTable
import com.project.detect_sdk_android.mvvm.bean.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Common : ViewModel {


    private var mutableCheckLiveData: MutableLiveData<Message>
    private var mStartMessage: MutableLiveData<Message>
    private var mEndMessage: MutableLiveData<Message>
    private var mutableLoginLiveData: MutableLiveData<Aa10>
    private var ware: CommonWare

    /**
     * 声明数据的仓库
     */
    constructor() {

    }

    init {
        ware = CommonWare()
        mutableLoginLiveData = MutableLiveData<Aa10>()
        mutableCheckLiveData = MutableLiveData<Message>()
        mStartMessage = MutableLiveData<Message>()
        mEndMessage = MutableLiveData<Message>()
    }

    /**
     * 提供一个数据访问方式
     */
    fun getAa10Data(): MutableLiveData<Aa10> {
        return mutableLoginLiveData
    }

    /**
     * 提供访问网络
     */
    fun mloadAa10() {
        GlobalScope.launch {
            var result = ware.mLoadAa10()
            withContext(Dispatchers.Default) {
                mutableLoginLiveData.value = result
            }
        }
    }

    /**
     * @method
     * SDK初始化的结果
     */
    fun getCheckEqu(): MutableLiveData<Message> {
        return mutableCheckLiveData;
    }

    fun mLoadCheckEqu(key: String, secret: String, packname: String) {
        GlobalScope.launch {
            var result = ware.mCheckEqu(key, secret, packname)
            withContext(Dispatchers.Default) {
                mutableCheckLiveData.postValue(result)
            }
        }
    }

    /**
     * 记录的开始
     */
    fun mLoadStart(equTable: EquTable) {
        GlobalScope.launch {
            var result = ware.mStart(equTable)
            withContext(Dispatchers.Default) {
                mStartMessage.postValue(result)
            }
        }
    }

    fun getStart(): MutableLiveData<Message> {
        return mStartMessage;
    }

    /**
     * 记录的结束
     */
    fun mLoadEnd(equTable: EquTable) {
        GlobalScope.launch {
            var result = ware.mEnd(equTable)
            withContext(Dispatchers.Default) {
                mEndMessage.postValue(result)
            }
        }
    }

    fun getEnd(): MutableLiveData<Message> {
        return mEndMessage;
    }
}