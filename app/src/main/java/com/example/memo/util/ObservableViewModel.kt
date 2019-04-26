package com.example.memo.util

import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.example.memo.ui.App
import java.util.*

open class ObservableViewModel(app:App): Observable,AndroidViewModel(app) {

    private val callbacks = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
            callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
            callbacks.add(callback)
    }
}