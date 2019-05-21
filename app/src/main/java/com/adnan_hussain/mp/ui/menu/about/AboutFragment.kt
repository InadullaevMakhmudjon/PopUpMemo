package com.adnan_hussain.mp.ui.menu.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import kotlinx.android.synthetic.main.layout_about.view.*
import android.text.Html.fromHtml as fromHtml1

class AboutFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_about,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("Email")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
        intent.type = "message/rfc822"
        val launcher = Intent.createChooser(intent,"Launch Email")
        view.Container.setOnClickListener {
            startActivity(launcher)
        }

        view.rate.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://www.yahoo.com")
            startActivity(intent)
        }

    }

}