package com.example.sportapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.sportapp.R

class CalendarAdapter internal constructor(private val context: Context,private val diasList:List<String>,private val agendaList:HashMap<String,List<String>>):
    BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return diasList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        val diasTitle = getGroup(groupPosition)as String

        if (convertView ==null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.calendario_dias,null)
        }
        val calendarioDias = convertView!!.findViewById<TextView>(R.id.calendario_dias)
        calendarioDias.setText(diasTitle)

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        val agendaTitle = getChild(groupPosition,childPosition) as String

        if (convertView ==null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.calendario_dias_agenda,null)
        }
        val agendaDias = convertView!!.findViewById<TextView>(R.id.calendario_dia_agenda)
        agendaDias.setText(agendaTitle)

        return convertView
    }


    override fun getChildrenCount(groupPosition: Int): Int {
        return this.agendaList[this.diasList[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.agendaList[this.diasList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return diasList.size
    }

}