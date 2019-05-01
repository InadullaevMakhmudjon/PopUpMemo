package com.adnan_hussain.mp.ui.splash

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.ui.main.MainActivity
import gr.net.maroulis.library.EasySplashScreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Splash implementation
         */
        val splash = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(5000)
            .withBackgroundColor(Color.parseColor("#A3A3A3"))
            .withLogo(R.mipmap.ic_launcher)
        setContentView(splash.create())
    }
}
