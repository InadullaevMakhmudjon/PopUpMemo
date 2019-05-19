package com.adnan_hussain.mp.ui.menu.samples.adpter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Music
import kotlinx.android.synthetic.main.item_song.view.*


class SongAdapter(val context: Context): RecyclerView.Adapter<SongViewHolder>() {

    lateinit var data:List<Music>
    var onItemClick:((Music,List<Music>,Int)->Unit)? = null
    var onLongClick:((String)->Unit)? = null
    var onRepeateClick:((List<Music>,Int)->Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_song,p0,false))
    }

    override fun getItemCount(): Int {
            return data.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(p0: SongViewHolder, p1: Int) {
            p0.itemView.title_music.text = data[p1].name
            p0.itemView.music_image.setImageDrawable(context.getDrawable(data[p1].image))


            p0.itemView.setOnClickListener {
                onItemClick?.invoke(data[p1],data,p1)
            }

           p0.itemView.setOnLongClickListener{
               onLongClick?.invoke("good")
                true
            }

            p0.itemView.looping.setOnClickListener{
                when(data[p1].condition){
                    0->{
                        data[p1].condition=1
                        p0.itemView.looping.setImageResource(R.drawable.ic_repeat_one_black_24dp)
                    }
                    1->{
                        data[p1].condition=0
                        p0.itemView.looping.setImageResource(R.drawable.ic_repeat_black_24dp)
                    }
                }
                onRepeateClick?.invoke(data,p1)
            }

        when(data[p1].condition){
            0->{
                p0.itemView.looping.setImageResource(R.drawable.ic_repeat_black_24dp)
            }
            1->{
                p0.itemView.looping.setImageResource(R.drawable.ic_repeat_one_black_24dp)
            }
        }
     }

    fun setValue(d:List<Music>){
        data = d
        notifyDataSetChanged()
    }

}