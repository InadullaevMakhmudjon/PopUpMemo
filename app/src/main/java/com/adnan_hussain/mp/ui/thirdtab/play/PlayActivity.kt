package com.adnan_hussain.mp.ui.thirdtab.play

import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuItem
import com.adnan_hussain.mp.R
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {

    var music_title=""
    var music_image_res=R.drawable.play_black
    var music_res=R.raw.music1

    lateinit var mp:MediaPlayer

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        music_title = intent.getStringExtra("music_title")
        music_res = intent.getIntExtra("music_res",R.raw.music1)
        music_image_res = intent.getIntExtra("music_image_res",R.drawable.pause_black)
        supportActionBar?.title = music_title
        main.setBackgroundResource(music_image_res)
        mp = MediaPlayer.create(this,music_res)
        mp.start()

        mp.isLooping = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.binueral,menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.navigation_play->{
                if(mp.isPlaying){
                    item.icon = getDrawable(R.drawable.ic_play_arrow_black_24dp)
                    mp.pause()
                }else{
                    item.icon = getDrawable(R.drawable.ic_pause)
                    mp.start()
                }
            }
            R.id.navigation_repeat->{
                if(mp.isPlaying) {
                    mp.stop()
                    mp.prepare()
                }
                mp.start()
            }
            android.R.id.home->{
                finish()
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mp.isPlaying){
            mp.stop()
            mp.release()
        }
    }
}
