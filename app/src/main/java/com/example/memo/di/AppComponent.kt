package com.example.memo.di

import com.example.memo.service.PopupService
import com.example.memo.ui.firsttab.PopupFragment
import com.example.memo.ui.firsttab.PopupRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(service:PopupService)
    fun inject(repository:PopupRepository)
    fun inject(fragment:PopupFragment)
}