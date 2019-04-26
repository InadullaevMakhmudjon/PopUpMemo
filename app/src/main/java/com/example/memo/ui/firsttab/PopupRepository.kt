package com.example.memo.ui.firsttab

import com.example.memo.data.db.model.Memo
import com.example.memo.data.db.model.Settings
import com.example.memo.ui.App

class PopupRepository(val app: App) {

    /**
     * Instance of database
     */
    var db = App.getDb

    /**
     * Getting memoes
     */
    var allData = db.getMemoDao.getAll()

    /**
     * getting settings
     */
    var settings = db.getSettings.getSettings()

    var number = db.getSettings.getNumber()

    /**
     * Insert function
     */
    fun insert(value:String){
        val memo = Memo()
        memo.text = value
        db.getMemoDao.insertMemo(memo)
    }

    /**
     * InsertSettings
     */
    fun insertSettings(value: Settings){
        db.getSettings.insertSettings(value)
    }

    /**
     * UpdateSettings
     */
    fun updateSettings(value: Settings){
        db.getSettings.update(value)
    }

}