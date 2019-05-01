package com.example.memo.ui.firsttab

import android.app.ActivityManager
import android.app.Dialog
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.memo.R
import com.example.memo.service.PopupService
import com.example.memo.ui.App
import com.example.memo.util.ObservableViewModel
import com.example.memo.util.showMessage


class PopUpViewModel(val context:App): ObservableViewModel(context) {

    private var shortAnimationDuration = MutableLiveData<Int>()
    private val repository = PopupRepository(context)
    var pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    var prefEdit = pref.edit()

    var allMemoes = repository.allData
    var settings = repository.settings

    var click = pref.getBoolean("button", false)

    fun addClick(v: View) {
        val dialog = Dialog(v.context)
        dialog.setContentView(R.layout.dialog_add_pupop)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        val text = dialog.findViewById<EditText>(R.id.message).text
        val button = dialog.findViewById<Button>(R.id.submit)

        dialog.setCancelable(true)
        button.setOnClickListener {
            repository.insert(text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }

    /**
     * Play button click event
     */
    fun playClick(v: View) {
        if (isMyServiceRunning(PopupService::class.java)) {
            context.stopService(Intent(context, PopupService::class.java))
            changeImage(v as FloatingActionButton)
            click = false
            prefEdit.putBoolean("button", click)
            prefEdit.apply()
        } else {
            if (getString().size >= 1) {
                val intent = Intent(context, PopupService::class.java)
                intent.putExtra("message", getString())
                context.startService(intent)

                changeImage(v as FloatingActionButton)
                click = true
                prefEdit.putBoolean("button", click)
                prefEdit.apply()
            } else {
                v.showMessage("Empty")
            }
        }

    }

    fun changeImage(playbutton: FloatingActionButton) {
        if (!isMyServiceRunning(PopupService::class.java)) {
            playbutton.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_play_arrow_black_24dp
                )
            )
        } else {
            playbutton.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_stop_black_24dp
                )
            )
        }
    }

    fun getString(): ArrayList<String> {
        var data = arrayListOf<String>()

        allMemoes.value?.forEach {
            data.add(it.text)
        }
        return data
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        for (service in manager!!.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}