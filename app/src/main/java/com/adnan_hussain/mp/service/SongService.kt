package com.adnan_hussain.mp.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.ui.App
import com.adnan_hussain.mp.ui.main.MainActivity

class SongService: Service() {

    lateinit var mp:MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val mName = intent?.getStringExtra("name")
        val mres = intent?.getIntExtra("resource",R.raw.music1)
        val condition = intent?.getIntExtra("condition",0)
        var id = intent?.getIntExtra("id",1)

        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,0)

        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this,App.CHANNEL_ID)
        } else {
            NotificationCompat.Builder(this)
        }
            .setContentTitle(mName)
            .setContentText("app is in progress")
            .setSmallIcon(R.drawable.ic_play_black_24dp)
            .setContentIntent(pendingIntent)
            .build()


        when(condition){
            0->{ //Condition when looping is dissable
                mp = if(mres!=null) {
                    MediaPlayer.create(this, mres)
                } else
                    MediaPlayer.create(this,R.raw.music1)

                mp.setOnCompletionListener {
                    stopSelf()
                }
            }
            1->{//Condition when looping is one song
                mp = if(mres!=null) {
                    MediaPlayer.create(this, mres)
                } else
                    MediaPlayer.create(this,R.raw.music1)

                mp.setOnCompletionListener {
                    mp.start()
                }
            }
        }

        mp.start()

        startForeground(1,notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        mp.stop()
    }

}