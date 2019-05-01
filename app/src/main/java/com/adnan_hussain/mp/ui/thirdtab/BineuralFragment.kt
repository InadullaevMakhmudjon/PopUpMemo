package com.adnan_hussain.mp.ui.thirdtab

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.data.db.model.Bineural
import com.adnan_hussain.mp.databinding.LayoutBineuralBinding
import com.adnan_hussain.mp.ui.App
import com.adnan_hussain.mp.ui.thirdtab.adapter.BineuralAdapter
import com.adnan_hussain.mp.ui.thirdtab.play.PlayActivity
import kotlinx.android.synthetic.main.layout_bineural.*

class BineuralFragment: Fragment() {

    lateinit var binding:LayoutBineuralBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_bineural,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this,BineuralVMFactory(this.activity!!.application as App))[BineuralViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        val adapter = BineuralAdapter()
        adapter.loadData(data())
        adapter.itemClick = {
            //Handle your task here
            val intent = Intent(this.context,PlayActivity::class.java)
            intent.putExtra("music_title",it.title)
            intent.putExtra("music_res",it.res)
            intent.putExtra("music_image_res",it.image)
            this.activity!!.startActivity(intent)
        }

        this.bineuralContainer.adapter = adapter
    }

    /**
     * List of default songs
     */
    fun data():List<Bineural>{
        val list = ArrayList<Bineural>()
        list.add(Bineural("Concentrate","Content",R.raw.concentrate,R.drawable.songbackground))
        list.add(Bineural("Euphoria Induction","Content",R.raw.euphoriainduction,R.drawable.ic_memo_pad))
        list.add(Bineural("Hemispherical Sync","Content",R.raw.hemisphericalsync,R.drawable.ic_launcher_background))
        list.add(Bineural("Insomnia","Content",R.raw.insomnia,R.drawable.ic_dashboard_black_24dp))
        list.add(Bineural("Memory Booster","Content",R.raw.memorybooster,R.drawable.ic_stop_black_24dp))
        list.add(Bineural("Problem Solving","Content",R.raw.problemsolving,R.drawable.orange))
        list.add(Bineural("Relaxation","Content",R.raw.relaxation,R.drawable.ic_writing))
        list.add(Bineural("Stress Managment","Content",R.raw.stressmanagment,R.drawable.play_black))
        list.add(Bineural("Study and Learn","Content",R.raw.learning,R.drawable.pause_black))
        return list
    }
}