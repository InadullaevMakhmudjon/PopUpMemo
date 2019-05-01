package com.adnan_hussain.mp.ui.firsttab

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adnan_hussain.mp.ui.App

class PopUpVMFactory(val app:App): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopUpViewModel(app) as T
    }
}