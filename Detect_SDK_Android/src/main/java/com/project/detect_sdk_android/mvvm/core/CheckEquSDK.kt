package com.project.detect_sdk_android.mvvm.core

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.neusoft.basecore.mvvm.viewmodel.Common
import com.project.detect_sdk_android.mvvm.bean.Message

class CheckEquSDK {
    companion object{
        private lateinit var context: Context
        fun open(key:String,secret:String,packname:String,context: FragmentActivity){
            this.context=context
            var model = ViewModelProviders.of(context).get(Common::class.java)
            model.mLoadCheckEqu(key,secret,packname);
            model.getCheckEqu().observe(context, object :Observer<Message>{
                override fun onChanged(t: Message?) {
                     Log.i("初始化INFO",t.toString());
                }
            })
        }

   }
}