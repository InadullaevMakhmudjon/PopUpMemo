package com.adnan_hussain.mp.ui.menu.settings

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.Navigation
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.util.showMessage
import kotlinx.android.synthetic.main.layout_settings.*
import kotlinx.android.synthetic.main.layout_settings.view.*

class SettingFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_settings,container,false)
    }

    /**
     * Default instances
     */
    lateinit var pref:SharedPreferences
    lateinit var edit:SharedPreferences.Editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        edit = pref.edit()

        view.close.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.popupFragment)
        }

        view.alphascroll.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                samplemessage.alpha = progress.toFloat()/100f
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }

        })

        view.checkmessage.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                view.samplemessage.setBackgroundColor(Color.WHITE)
            }else{
                view.samplemessage.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        /**
         * View Click event,
         */
        view.submitbtn.setOnClickListener {
            val color = view.samplemessage.currentTextColor
            val ischecked = view.checkmessage.isChecked
            val alpha = view.samplemessage.alpha
            val duration = view.duration.text.toString().toInt()
            val frequency = (view.frequency.text.toString().toInt())*1000
            if(frequency<1 || duration<100){
                it.showMessage("Please input appropriate value")
            }else {
                edit.putInt("color", color)
                edit.putBoolean("ischecked", ischecked)
                edit.putFloat("alpha", alpha)
                edit.putInt("duration", duration)
                edit.putInt("freq", frequency)
                edit.apply()
                Navigation.findNavController(view).navigate(R.id.popupFragment)
            }
        }
        initialize(view)
        onColorClick(view)
    }

    /**
     * Initializing default values
     */
    fun initialize(v:View){
        v.duration.setText((pref.getInt("duration",300)).toString())
        v.frequency.setText(((pref.getInt("freq",1000))/1000).toString())
        if((pref.getBoolean("ischecked",false))) v.samplemessage.setBackgroundColor(Color.WHITE)
        else v.samplemessage.setBackgroundColor(Color.TRANSPARENT)
        v.samplemessage.setTextColor(pref.getInt("color",Color.RED))
        v.checkmessage.isChecked = pref.getBoolean("ischecked",false)
        v.alphascroll.progress = (pref.getFloat("alpha",1f) * 100).toInt()
    }

    fun onColorClick(v:View){
            v.black.setOnClickListener {
                samplemessage.setTextColor((Color.parseColor("#000000")))
            }
            v.red.setOnClickListener{
                samplemessage.setTextColor((Color.parseColor("#FF0000")))
            }
            v.orange.setOnClickListener{
                samplemessage.setTextColor((Color.parseColor("#FF6600")))
            }
            v.green.setOnClickListener{
                samplemessage.setTextColor((Color.parseColor("#37FF00")))
            }
    }

  }