package com.example.memo.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.example.memo.R
import com.example.memo.ui.main.MainActivity

open class NotificationGenerator {

    companion object{
        const val NOTIFY_PAUSE = "com.example.memo.notification.pause"
        private const val CHANNEL_ID = "simple_id"
        private const val CHANNEL_NAME = "simple_name"
        private const val CHANNEL_DESC = "simple_desc"

        private const val NOTIFICATION_ID_OPEN_ACTIVITY = 9



    }
}