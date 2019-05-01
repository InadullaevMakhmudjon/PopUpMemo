package com.adnan_hussain.mp.util

import android.view.View
import android.widget.Toast

fun View.showMessage(message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}