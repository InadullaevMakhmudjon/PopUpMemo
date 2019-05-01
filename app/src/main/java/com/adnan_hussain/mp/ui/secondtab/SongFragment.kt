package com.adnan_hussain.mp.ui.secondtab

import android.app.ActivityManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
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

        adapter.setValue(getlist())
        val intent = Intent(this.context,SongService::class.java)

        adapter.onItemClick = {
            if(isMyServiceRunning(SongService::class.java))
                this.activity!!.stopService(intent)

            intent.putExtra("name",it.name)
            intent.putExtra("resource",it.resource)
            this.activity!!.startService(intent)
        }

        adapter.onLongClick = {
            this.activity!!.stopService(intent)
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
        list.add(Music("Hello", R.raw.music1))
        list.add(Music("Goodbye",R.raw.music2))
        list.add(Music("How",R.raw.music3))
        list.add(Music("Wonderful",R.raw.music4))
        return list
    }




}