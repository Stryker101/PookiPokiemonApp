package com.example.week8pokemon

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast

object Extensions {

    fun <T> log(data: T){
        Log.d("INFORMATION", "log: ${data.toString()}")
    }


    fun <T> toast(context: Context, data: T){
        Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show()
    }
}
