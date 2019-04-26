package com.example.memo.ui.menu.samples.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.memo.R
import com.example.memo.data.db.model.Music
import kotlinx.android.synthetic.main.item_song.view.*


class SongAdapter(val context: Context): RecyclerView.Adapter<SongViewHolder>() {

    lateinit var data:List<Music>
    var onItemClick:((Music)->Unit)? = null
    var onLongClick:((String)->Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_song,p0,false))
    }

    override fun getItemCount(): Int {
            return data.size
    }

    override fun onBindViewHolder(p0: SongViewHolder, p1: Int) {
            p0.itemView.title_music.text = data[p1].name

            p0.itemView.setOnClickListener {
                onItemClick?.invoke(data[p1])
            }
        p0.itemView.setOnLongClickListener{
               onLongClick?.invoke("good")
                true
        }
     }

    fun setValue(d:List<Music>){
        data = d
        notifyDataSetChanged()
    }

}