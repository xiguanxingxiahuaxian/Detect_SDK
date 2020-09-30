package com.project.detect_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.project.detect_sdk_android.mvvm.core.CheckEquSDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CheckEquSDK.open("1","1","1",this);
    }
}