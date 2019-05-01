package com.adnan_hussain.mp.ui.thirdtab.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Bineural
import kotlinx.android.synthetic.main.item_bineural.view.*

class BineuralAdapter: RecyclerView.Adapter<BineuralViewHolder>() {

    /**
     * Adapter data
     */
    lateinit var data:List<Bineural>
    var itemClick:((Bineural)->Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BineuralViewHolder {
            return BineuralViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_bineural,p0,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * BInding with UI
     */
    override fun onBindViewHolder(p0: BineuralViewHolder, p1: Int) {
        p0.itemView.title.text = data[p1].title
        p0.itemView.content.text = data[p1].content
        p0.itemView.setOnClickListener {
            itemClick?.invoke(data[p1])
        }
    }

    fun loadData(d:List<Bineural>){
        data = d
        notifyDataSetChanged()
    }
}