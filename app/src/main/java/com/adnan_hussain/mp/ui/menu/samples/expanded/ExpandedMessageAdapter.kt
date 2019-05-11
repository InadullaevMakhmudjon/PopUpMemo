package com.adnan_hussain.mp.ui.menu.samples.expanded

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import android.widget.TextView
import com.adnan_hussain.mp.R
import com.adnan_hussain.mp.util.showMessage

class ExpandedMessageAdapter(val context: Context,
                             private val expandableTitels: List<String>,
                             private val expandableData: HashMap<String, List<String>>): BaseExpandableListAdapter(){
    //Total titles of expandable list
    override fun getGroup(groupPosition: Int): Any {
            return expandableTitels[groupPosition]
    }

    //Whether child accessible
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return true
    }

    override fun hasStableIds(): Boolean {
            return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val title:String = getGroup(groupPosition) as String

       return if(convertView != null){
            val titleText = convertView.findViewById<View>(R.id.listTitle) as TextView
            titleText.setTypeface(null, Typeface.BOLD)
            titleText.text = title
            convertView
        }else{
           val v = LayoutInflater.from(context).inflate(R.layout.message_expandable_group,null)
            val titleText = v.findViewById<View>(R.id.listTitle) as TextView
            titleText.setTypeface(null, Typeface.BOLD)
            titleText.text = title
            v
        }
    }

    override fun getChildrenCount(groupPosition: Int): Int {
            val data = expandableData[expandableTitels[groupPosition]]
            return data?.size ?: 0
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        val data = expandableData[expandableTitels[groupPosition]]
        return data?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val data = expandableData.get(expandableTitels[groupPosition])
        val childText = if(data!=null) data[childPosition] else ""
        return if(convertView != null){
            val titleText = convertView.findViewById<View>(R.id.messageExpandedItem) as TextView
            titleText.setTypeface(null, Typeface.BOLD)
            titleText.text = childText
            convertView
        }else{
            LayoutInflater.from(context).inflate(R.layout.message_expandable_item,null)
        }
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return expandableTitels.size
    }
}