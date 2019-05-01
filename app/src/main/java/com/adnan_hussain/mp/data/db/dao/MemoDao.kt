package com.adnan_hussain.mp.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.adnan_hussain.mp.data.db.model.Memo

@Dao
interface MemoDao {

    @Query("SELECT * FROM Memo")
    fun getAll():LiveData<List<Memo>>

    @Insert
    fun insertMemo(memo:Memo)

    @Delete
    fun deleteMemo(memo:Memo)

    @Update
    fun updateMemo(memo:Memo)
}