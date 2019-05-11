package com.adnan_hussain.mp.ui.menu.about

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
        @Suppress("DEPRECATION")
        view.contactMail.text = fromHtml1("<a style=\"color: #FFF; \" href=\"mailto:adnan.hussain@example.com\">Click me</a>")
        view.contactMail.movementMethod = LinkMovementMethod.getInstance()
    }

}