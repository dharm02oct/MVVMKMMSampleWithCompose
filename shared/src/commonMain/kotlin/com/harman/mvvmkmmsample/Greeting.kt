package com.harman.mvvmkmmsample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}