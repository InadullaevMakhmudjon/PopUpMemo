package com.adnan_hussain.mp.ui.menu.docs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.ui.menu.docs.adapter.ExpandableAdapter
import com.adnan_hussain.mp.ui.menu.samples.expanded.ExpandedMessageAdapter
import kotlinx.android.synthetic.main.layout_documentation.view.*
import kotlinx.android.synthetic.main.layout_sample_message.view.*

class DocsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_documentation,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * List data initializing. You can do in as separately class if data goes to large
         */
        val listData = HashMap<String,List<String>>()

        val books = mutableListOf<String>()
        books.add("Docs")
        books.add("Docs")
        books.add("Docs")
        books.add("Docsbook")

        val guns = mutableListOf<String>()
        guns.add("Docs my Gun")
        guns.add("Docs not my Gun")
        guns.add("Docs be my Gun")
        guns.add("Docs not be my Gun")

        val laptops = mutableListOf<String>()
        laptops.add("this is my Laptop")
        laptops.add("this is not my Laptop")
        laptops.add("this may be my Laptop")
        laptops.add("this may not be my Laptop")

        listData.put("Books",books)
        listData.put("Guns",guns)
        listData.put("Laptops",laptops)

        val adapter = ExpandableAdapter(this.context!!, listData.keys.toList(),listData)
        view.expandabledocs.setAdapter(adapter)
    }

}