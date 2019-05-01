package com.adnan_hussain.mp.ui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.adnan_hussain.mp.data.db.AppDb

class App: Application() {

    companion object {
        const val CHANNEL_ID = "simple_id"
        const val CHANNEL_NAME = "simple_name"
        const val CHANNEL_DESC = "simple_desc"

       private lateinit var db:AppDb
        val getDb:AppDb get()= db
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDb.getInstance(this)
        initialize(this)
    }

    /**
     * CHecking SDK version for notification builder
     */
    private fun initialize(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW)
            channel.description = CHANNEL_DESC
            channel.setSound(null,null)
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }
    }
}