package com.adnan_hussain.mp.di

import android.arch.persistence.room.Room
import android.content.Context
import com.adnan_hussain.mp.data.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context:Context) {

    @Provides
    @Singleton
    fun provideDb():AppDb{
        return Room.databaseBuilder(context,AppDb::class.java,"MemoApp")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}