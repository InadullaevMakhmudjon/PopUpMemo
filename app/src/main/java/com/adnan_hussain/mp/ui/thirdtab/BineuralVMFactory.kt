package com.adnan_hussain.mp.ui.thirdtab

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adnan_hussain.mp.ui.App

class BineuralVMFactory(val app:App): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BineuralViewModel(app) as T
    }
}