package com.adnan_hussain.mp.ui.firsttab

import android.app.ActivityManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.databinding.LayoutPopupBinding
import com.adnan_hussain.mp.service.PopupService
import com.adnan_hussain.mp.ui.App
import com.adnan_hussain.mp.ui.firsttab.adapter.MemoAdapter
import com.adnan_hussain.mp.util.showMessage
import kotlinx.android.synthetic.main.layout_popup.*

class PopupFragment : Fragment() {

    /**
     * binding and database instances
     */
    lateinit var binding:LayoutPopupBinding
    var db = App.getDb

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_popup,container,false)
        return binding.root
    }

    /**
     * Initializing ViewModel and binding with UI
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val app = this.activity!!.application as App
        val pref = PreferenceManager.getDefaultSharedPreferences(app)
        val button = pref.getBoolean("button",false)
        val viewModel = ViewModelProviders.of(this,PopUpVMFactory(app))[PopUpViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        val adapter = MemoAdapter(db)

        //Observer, listens to database change
        viewModel.allMemoes.observe(this, Observer {
            if(it!=null){
                viewModel.canLoad.value = it.size + 1 <= 5
                adapter.loadData(it)
            }
        })

        //Initializing default button icon
        if(!isMyServiceRunning(PopupService::class.java)){
            playbutton.setImageDrawable(
                ContextCompat.getDrawable(
                    app,
                    R.drawable.ic_play_arrow_black_24dp
                )
            )
        }else{
            playbutton.setImageDrawable(
                ContextCompat.getDrawable(
                    app,
                    R.drawable.ic_stop_black_24dp
                )
            )
        }
        itemholder.adapter = adapter
    }


    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = this.activity!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        for (service in manager!!.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}