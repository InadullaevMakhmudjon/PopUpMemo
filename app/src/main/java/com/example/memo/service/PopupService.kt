package com.example.memo.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.example.memo.R
import java.util.*
import kotlin.random.Random


class PopupService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    val mTimer = Timer()
    lateinit var pref: SharedPreferences
    lateinit var wm:WindowManager
    lateinit var textView:TextView
    lateinit var data:ArrayList<String>

    var isChecked = false
    var colorText = Color.BLACK
    var alpha = 0.5f
    var duration = 100L // in mill seconds
    var frequency = 500L //in mill seconds


    override fun onCreate() {
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        var str = pref.getString("arraylist","Not,Not")
        data = str.split(",") as ArrayList<String>
        isChecked = pref.getBoolean("ischecked",false)
        colorText = pref.getInt("color",Color.BLACK)
        alpha = pref.getFloat("alpha",1F)
        duration = (pref.getInt("duration",1000)).toLong()
        frequency = (pref.getInt("freq",5000)).toLong()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent != null) {
            val editor = pref.edit()
            data = intent.getStringArrayListExtra("message")
            val bind = StringBuilder()
            data.forEach {
                bind.append(it).append(",")
            }
            editor.putString("arraylist",bind.toString())
            editor.apply()
        }

        wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        textView = inflater.inflate(R.layout.messages, null) as TextView
        mTimer.scheduleAtFixedRate(TimeDisplayTimerTask(wm,textView,this), 0, frequency)
        return super.onStartCommand(intent, flags, startId)
    }

    class TimeDisplayTimerTask(val wm: WindowManager,val textView: TextView,val service:PopupService) : TimerTask() {

        val data = service.data
        var isChecked = service.isChecked
        var colorText = service.colorText
        var alpha = service.alpha
        var duration = service.duration

        var params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT)


        val handler = Handler()

        fun textDecoration(textView: TextView) {
            try {
                textView.textSize = 25f

                if(isChecked)textView.setBackgroundColor(Color.WHITE)
                else textView.setBackgroundColor(Color.TRANSPARENT)
                textView.alpha = alpha
                textView.setTextColor(colorText)
                textView.setPadding(10, 10, 10, 10)
            }catch (e:Exception){

            }
        }

        override fun run() {

            val runnable = Runnable {
                val displaymetrics = DisplayMetrics()

                wm.defaultDisplay.getMetrics(displaymetrics)
                val width = displaymetrics.widthPixels
                val height = displaymetrics.heightPixels

                params.x = Random.nextInt(width - 10)
                params.y = Random.nextInt(height - 10)
                val r = Random
                if(data!=null)
                    textView.text = data[r.nextInt(data.size)]

                textDecoration(textView)

                if (textView.isShown)
                    wm.removeView(textView)

                wm.addView(textView, params)
            }

            handler.post(runnable)

            handler.postDelayed({
                if(textView.isShown)
                    wm.removeView(textView)
            },duration)
        }

        override fun cancel(): Boolean {
            if(textView.isShown)
                wm.removeView(textView)
            return super.cancel()
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        if(textView.isShown)
            wm.removeView(textView)
        try{
            mTimer.cancel()
        }catch (e:Exception){

        }

    }

}