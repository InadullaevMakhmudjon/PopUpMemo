package com.adnan_hussain.mp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.adnan_hussain.mp.data.db.dao.MemoDao
import com.adnan_hussain.mp.data.db.dao.SettingsDao
import com.adnan_hussain.mp.data.db.model.Memo
import com.adnan_hussain.mp.data.db.model.Settings
import com.adnan_hussain.mp.di.SingletonHolder


@Database(entities = [Memo::class,Settings::class], version = 3,exportSchema = false)
abstract class AppDb: RoomDatabase() {
    abstract val getMemoDao:MemoDao
    abstract val getSettings:SettingsDao

    companion object : SingletonHolder<AppDb, Context>({
        Room.databaseBuilder(it,AppDb::class.java,"MemoApp")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    })
}