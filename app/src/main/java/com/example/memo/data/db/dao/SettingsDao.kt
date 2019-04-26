package com.example.memo.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.memo.data.db.model.Settings

@Dao
interface SettingsDao {

    @Query("SELECT * FROM Settings where id = 0")
    fun getSettings():LiveData<Settings>

    @Query("SELECT COUNT(*) FROM Settings")
    fun getNumber():LiveData<Int>

    @Delete
    fun detedeSettings(settings:Settings)

    @Insert
    fun insertSettings(settings: Settings)

    @Update
    fun update(settings: Settings)
}