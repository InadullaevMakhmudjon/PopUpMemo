package com.adnan_hussain.mp.util

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast


fun View.showMessage(message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}

fun Context.showMessage(message:String) {
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.showDialog(title:String,subtitle:String) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(subtitle)
        .setCancelable(true)
        .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(browserIntent)
        })
        .setNegativeButton(android.R.string.no, null)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}