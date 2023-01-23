package com.sample.mvvmkmmsample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}