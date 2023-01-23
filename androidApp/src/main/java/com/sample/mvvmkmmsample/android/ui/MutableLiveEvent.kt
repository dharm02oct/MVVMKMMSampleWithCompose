package com.sample.mvvmkmmsample.android.ui

import android.os.Build
import android.util.ArraySet
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class MutableLiveEvent<T : MutableLiveEvent.EventArgs<Any>> : MutableLiveData<T>() {

    @RequiresApi(Build.VERSION_CODES.M)
    internal val observers = ArraySet<PendingObserver<in T>>()

    @RequiresApi(Build.VERSION_CODES.M)
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = PendingObserver(observer)
        observers.add(wrapper)

        super.observe(owner, wrapper)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun observeForever(observer: Observer<in T>) {
        val wrapper = PendingObserver(observer)
        observers.add(wrapper)

        super.observeForever(observer)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @MainThread
    override fun removeObserver(observer: Observer<in T>) {

        when (observer) {
            is PendingObserver -> {
                observers.remove(observer)
                super.removeObserver(observer)
            }
            else -> {
                val pendingObserver = observers.firstOrNull { it.wrappedObserver == observer }
                if (pendingObserver != null) {
                    observers.remove(pendingObserver)
                    super.removeObserver(pendingObserver)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @MainThread
    override fun setValue(event: T?) {
        observers.forEach { it.awaitValue() }
        super.setValue(event)
    }

    internal class PendingObserver<T : EventArgs<Any>>(val wrappedObserver: Observer<in T>) :
        Observer<T> {

        private var pending = false

        override fun onChanged(event: T?) {
            if (pending && event?.handled != true) {
                pending = false
                wrappedObserver.onChanged(event)
            }
        }

        fun awaitValue() {
            pending = true
        }
    }

    open class EventArgs<out T>(private val content: T?) {

        var handled: Boolean = false

        val data: T?
            get() = if (handled) null else content
    }
}