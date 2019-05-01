package com.adnan_hussain.mp.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class Memo {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    var text:String = ""

}