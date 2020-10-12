package com.project.detect_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.project.detect_sdk_android.mvvm.core.CheckEquSDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //注册信息收集SDK
        CheckEquSDK.open("1","1","1",this);
    }

    override fun onDestroy() {
        super.onDestroy()
        CheckEquSDK.onStop()
    }
}