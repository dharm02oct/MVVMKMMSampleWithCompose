package com.harman.mvvmkmmsample.android.vo

import com.harman.mvvmkmmsample.android.ui.MutableLiveEvent


class StateEvent<out T> private constructor(data: State<T>) :
    MutableLiveEvent.EventArgs<State<T>>(data) {

    companion object {
        fun <T> success(value: T) = StateEvent(State.Success(value))
        fun error() = StateEvent(State.Error)
    }
}