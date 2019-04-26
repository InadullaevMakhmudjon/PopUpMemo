package com.example.memo.ui.firsttab.adapter

import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.memo.R
import com.example.memo.data.db.AppDb
import com.example.memo.data.db.model.Memo
import kotlinx.android.synthetic.main.item_text_popup.view.*

class MemoAdapter(val db: AppDb): RecyclerView.Adapter<MemoViewHolder>() {

    var data = listOf<Memo>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MemoViewHolder {
        return MemoViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_text_popup,p0,false))
    }

    override fun getItemCount(): Int {
        return data?.size
    }

    override fun onBindViewHolder(p0: MemoViewHolder, p1: Int) {
        p0.itemView.title.text = data[p1].text
        p0.itemView.delete.setOnClickListener{
            db.getMemoDao.deleteMemo(data[p1])
        }
        p0.itemView.setOnClickListener {
            dialogInit(p0.itemView,data[p1])
        }
    }

    /**
     * Binding UI with Data
     */
    fun dialogInit(v:View,memo:Memo){
        val dialog = Dialog(v.context)
        dialog.setContentView(R.layout.dialog_add_pupop)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        val Edittext = dialog.findViewById<EditText>(R.id.message)
        val button = dialog.findViewById<Button>(R.id.submit)
        Edittext.setText(memo.text)

        dialog.setCancelable(true)
        button.setOnClickListener {
            memo.text = Edittext.text.toString()
            db.getMemoDao.updateMemo(memo)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun loadData(d:List<Memo>){
        data = d
        notifyDataSetChanged()
    }
}