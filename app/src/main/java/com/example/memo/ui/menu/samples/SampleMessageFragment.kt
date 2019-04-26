package com.example.memo.ui.menu.samples

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.R
import com.example.memo.data.db.model.Sample
import com.example.memo.ui.menu.samples.adpter.SMAdapter
import kotlinx.android.synthetic.main.layout_sample_message.*

class SampleMessageFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_sample_message,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = SMAdapter(this.activity!!)
        adapter.setValue(data())

        samplesContainer.adapter=adapter

    }

    /**
     * List of default data
     */
    fun data():List<Sample>{
        var list = ArrayList<Sample>()
        list.add(Sample("what message"))
        list.add(Sample("when message"))
        list.add(Sample("how message"))
        list.add(Sample("where message"))
        list.add(Sample("why message"))
        return list
    }
}