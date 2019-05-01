package com.adnan_hussain.mp.ui.menu.samples.adpter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Sample
import kotlinx.android.synthetic.main.item_sample_message.view.*


class SMAdapter(val context: Context): RecyclerView.Adapter<SongViewHolder>() {

    lateinit var data:List<Sample>
    var itemClick:((Sample)->Unit)?=null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_sample_message,p0,false))
    }

    override fun getItemCount(): Int {
            return data.size
    }

    override fun onBindViewHolder(p0: SongViewHolder, p1: Int) {
            p0.itemView.title.text = data[p1].message

        /**
         * Item Onclick
         */
        p0.itemView.setOnClickListener {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                val clip = ClipData.newPlainText(data[p1].message, data[p1].message)
                clipboard!!.primaryClip = clip
                itemClick?.invoke(data[p1])
            }
     }

    fun setValue(d:List<Sample>){
        data = d
        notifyDataSetChanged()
    }

}