package com.harman.mvvmkmmsample.di

import com.harman.mvvmkmmsample.ContextArgs
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object KoinInjector {
    var mContextArgs : ContextArgs? = null

    fun provideContext(mContextArgs: ContextArgs): ContextArgs? {
        this.mContextArgs = mContextArgs
        return mContextArgs
    }
}