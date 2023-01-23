package com.sample.mvvmkmmsample

import android.content.Context

actual class ContextArgs(var mContext: Context)

 actual fun isNetworkAvailable() : Boolean{
   return true
}
