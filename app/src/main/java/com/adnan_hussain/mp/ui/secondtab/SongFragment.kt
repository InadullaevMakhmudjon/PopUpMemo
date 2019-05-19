package com.adnan_hussain.mp.ui.secondtab

import android.app.ActivityManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Music
import com.adnan_hussain.mp.databinding.LayoutSongsBinding
import com.adnan_hussain.mp.service.SongService
import com.adnan_hussain.mp.ui.App
import com.adnan_hussain.mp.ui.menu.samples.adpter.SongAdapter
import kotlinx.android.synthetic.main.layout_songs.*


class SongFragment : Fragment() {

        lateinit var binding:LayoutSongsBinding
        var condition=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_songs,container,false)
        return binding.root
    }

    /**
     * Override method
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this,SongVMFactory(this.activity!!.application as App))[SongViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        val adapter = SongAdapter(this.context!!)
        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val editor = pref.edit()
        condition = pref.getInt("condition",0)

        adapter.setValue(getlist())
        val intent = Intent(this.context,SongService::class.java)

        adapter.onItemClick = {item,data,state->
            if(isMyServiceRunning(SongService::class.java))
                this.activity!!.stopService(intent)

            intent.putExtra("name",item.name)
            intent.putExtra("resource",item.resource)
            intent.putExtra("condition",item.condition)
            intent.putExtra("id",item.id)
            editor.apply()

            adapter.setValue(data)
            this.activity!!.startService(intent)
        }

        adapter.onLongClick = {
            this.activity!!.stopService(intent)
        }

        adapter.onRepeateClick = {data,index->
            data.forEach {
                 if(it.id != data[index].id) it.condition = 0
            }
            adapter.setValue(data)

            editor.putInt("condition",data[index].condition)
            editor.apply()
        }

        music_container.adapter = adapter


    }

    /**
     * function to check service is running
     */
    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = this.activity!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        for (service in manager!!.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    /**
     * List of default musics
     */
    fun getlist():List<Music>{
        val list = ArrayList<Music>()
        for(a in 1..4){
            val resID = resources.getIdentifier("music$a", "raw", this.activity!!.packageName)
            list.add(Music(a,"Hello $a", resID, R.drawable.play_black,condition))
        }
        return list
    }




}