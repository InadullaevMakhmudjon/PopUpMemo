package com.adnan_hussain.mp.ui.menu.samples

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Sample
import com.adnan_hussain.mp.ui.menu.samples.expanded.ExpandedMessageAdapter
import kotlinx.android.synthetic.main.layout_sample_message.view.*

class SampleMessageFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_sample_message,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * List data initializing. You can do in as separately class if data goes to large
         */
        val listData = HashMap<String,List<String>>()

        val books = mutableListOf<String>()
        books.add("this is my book")
        books.add("this is not my book")
        books.add("this may be my book")
        books.add("this may not be my book")

        val guns = mutableListOf<String>()
        guns.add("this is my Gun")
        guns.add("this is not my Gun")
        guns.add("this may be my Gun")
        guns.add("this may not be my Gun")

        val laptops = mutableListOf<String>()
        laptops.add("this is my Laptop")
        laptops.add("this is not my Laptop")
        laptops.add("this may be my Laptop")
        laptops.add("this may not be my Laptop")

        listData.put("Books",books)
        listData.put("Guns",guns)
        listData.put("Laptops",laptops)

        val adapter = ExpandedMessageAdapter(this.context!!, listData.keys.toList(),listData)
        view.expandableParent.setAdapter(adapter)
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