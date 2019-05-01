package com.adnan_hussain.mp.notify

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action.equals(NotificationGenerator.NOTIFY_PAUSE)){
                Toast.makeText(context,"Play clicked",Toast.LENGTH_SHORT).show()
            }

    }
}