package com.runprof.usedbrands.ui.exts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.runprof.usedbrands.pojo.Event

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner) {
        it.getValue()?.let(observer)
    }
}