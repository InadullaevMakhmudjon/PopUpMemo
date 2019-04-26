package com.example.memo.ui.thirdtab

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.R
import com.example.memo.data.db.model.Bineural
import com.example.memo.databinding.LayoutBineuralBinding
import com.example.memo.ui.App
import com.example.memo.ui.thirdtab.adapter.BineuralAdapter
import com.example.memo.ui.thirdtab.play.PlayActivity
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
            this.activity!!.startActivity(intent)
        }

        this.bineuralContainer.adapter = adapter
    }

    /**
     * List of default songs
     */
    fun data():List<Bineural>{
        val list = ArrayList<Bineural>()
        list.add(Bineural("Concentrate","Content",R.raw.concentrate))
        list.add(Bineural("Euphoria Induction","Content",R.raw.euphoriainduction))
        list.add(Bineural("Hemispherical Sync","Content",R.raw.hemisphericalsync))
        list.add(Bineural("Insomnia","Content",R.raw.insomnia))
        list.add(Bineural("Memory Booster","Content",R.raw.memorybooster))
        list.add(Bineural("Problem Solving","Content",R.raw.problemsolving))
        list.add(Bineural("Relaxation","Content",R.raw.relaxation))
        list.add(Bineural("Stress Managment","Content",R.raw.stressmanagment))
        list.add(Bineural("Study and Learn","Content",R.raw.learning))
        return list
    }
}