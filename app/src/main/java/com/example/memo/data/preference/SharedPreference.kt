package com.example.memo.data.preference

import android.content.Context

class SharedPreference(val context:Context){

    private var preference= context.getSharedPreferences("settings",Context.MODE_PRIVATE)

    fun getDuration():Int{
        return preference.getInt("duration",10)
    }

    fun getFrequency():Int{
        return preference.getInt("frequency",10)
    }

    fun getAlpha():Int{
        return preference.getInt("alpha",1)
    }

    fun getColor():String{
        return preference.getString("color","#000")
    }

    fun getPaused():Boolean{
        return preference.getBoolean("check",false)
    }

    fun setDuration(value:Int){
         preference.edit().putInt("duration",value)
    }

    fun setFrequency(value:Int){
        preference.edit().putInt("frequency",value)
    }

    fun setAlpha(value:Int){
        preference.edit().putInt("alpha",value)
    }

    fun setColor(value:String){
        preference.edit().putString("color",value)
    }

    fun setPaused(value:Boolean){
        preference.edit().putBoolean("check",value)
    }


}