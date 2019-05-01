package com.example.memo.ui.main

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.Navigation
import com.example.memo.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.popupFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.songFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.bineuralFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val int = intent.getIntExtra("name",2)
        val pr = PreferenceManager.getDefaultSharedPreferences(this)
        val edit = pr.edit()
        edit.putInt("width",this.windowManager.defaultDisplay.width)
        edit.putInt("height",this.windowManager.defaultDisplay.height)
        edit.apply()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var manager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if(manager.isMusicActive){
            Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.songFragment)
        }

        checkPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermission(){
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    /**
     * Options item selected, when you click on UI
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.example->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.sampleMessageFragment)
            }
            R.id.docs->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.docsFragment)
            }
            R.id.settings->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.settingFragment)
            }
            R.id.about->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.aboutFragment)
            }
        }
        return true
    }

}
