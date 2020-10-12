package com.project.detect_sdk_android.mvvm.core

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.neusoft.basecore.mvvm.viewmodel.Common
import com.project.detect_sdk_android.mvvm.bean.EquTable
import com.project.detect_sdk_android.mvvm.config.AppConfig.Companion.RESULTSTATUS
import java.lang.reflect.Method
import java.util.*


class CheckEquSDK {

    companion object {
        private lateinit var serial: String
        private lateinit var context: FragmentActivity


        fun open(key: String, secret: String, packname: String,context:FragmentActivity) {
            this.context=context
            var model = ViewModelProviders.of(context).get(Common::class.java)
            model.mLoadCheckEqu(key, secret, packname);
            model.getCheckEqu().observeForever(Observer {
                Log.i("初始化INFO", it.toString());
                when (it!!.code) {
                    "1" -> {
                        RESULTSTATUS = true
                    }
                    else -> {
                        RESULTSTATUS = false
                    }
                }
                onStart()
            })
            //获取当前的设备号
            onGetSn()

        }

        fun onGetSn(): String {
            try {
                val c = Class.forName("android.os.SystemProperties")

                val get: Method = c.getMethod("get", String::class.java)

                serial = get.invoke(c, "ro.serialno") as String

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return serial
        }

        fun onStart() {
            if(!RESULTSTATUS){
                return
            }
            /**
             * 启动次数
             * 启动时间
             */
            var model = ViewModelProviders.of(context).get(Common::class.java)
            var equTable: EquTable = EquTable()
            equTable.count = "1"
            equTable.starttime = Date().toString();
            equTable.tstate = "true"
            equTable.equid = serial
            model.mLoadStart(equTable)
            model.getStart().observe(context, Observer {
                Log.i("初始化INFO", it.toString());
            })
        }

        fun onStop() {
            //设备号的终止
            /**
             * 启动终止
             * 终止时间
             */
            if(!RESULTSTATUS){
                return
            }
            var model = ViewModelProviders.of(context).get(Common::class.java)
            var equTable: EquTable = EquTable()
            equTable.endtime = Date().toString();
            equTable.tstate = "false"
            equTable.equid = serial
            model.mLoadEnd(equTable)
            model.getEnd().observe(context, Observer {
                Log.i("初始化INFO", it.toString());
            })
        }
    }
}