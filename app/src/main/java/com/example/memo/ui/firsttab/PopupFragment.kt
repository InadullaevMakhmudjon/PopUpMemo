package com.example.memo.ui.firsttab

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.R
import com.example.memo.data.db.AppDb
import com.example.memo.data.db.model.Settings
import com.example.memo.data.preference.SharedPreference
import com.example.memo.databinding.LayoutPopupBinding
import com.example.memo.notify.NotificationGenerator
import com.example.memo.ui.App
import com.example.memo.ui.firsttab.adapter.MemoAdapter
import com.example.memo.util.showMessage
import kotlinx.android.synthetic.main.layout_popup.*
import kotlinx.android.synthetic.main.layout_popup.view.*
import javax.inject.Inject

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
            if(it!=null) adapter.loadData(it)
        })

        //Initializing default button icon
        if(!button){
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

}