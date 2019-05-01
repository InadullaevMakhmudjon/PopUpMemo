package com.adnan_hussain.mp.di

import com.adnan_hussain.mp.service.PopupService
import com.adnan_hussain.mp.ui.firsttab.PopupFragment
import com.adnan_hussain.mp.ui.firsttab.PopupRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(service:PopupService)
    fun inject(repository:PopupRepository)
    fun inject(fragment:PopupFragment)
}