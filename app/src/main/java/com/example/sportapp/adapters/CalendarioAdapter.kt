package com.example.sportapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.databinding.CalendarioItemBinding
import com.example.sportapp.models.Calendario
//import com.example.sportapp.ui.CalendarioListarFragmentDirections

class CalendarioAdapter : RecyclerView.Adapter<CalendarioAdapter.CalendarioViewHolder>() {

    var collectors: List<Calendario> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarioViewHolder {
        val withDataBinding: CalendarioItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CalendarioViewHolder.LAYOUT,
            parent,
            false
        )
        return CalendarioViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CalendarioViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.calendariolista = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {

            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
            Log.i("CalendarioAdapter", "se dio clic en el dia:" + collectors[position].fecha)

            val coll = collectors[position]
            Log.i("CalendarioAdapter", coll.toString())
            /*val action = CalendarioListarFragmentDirections.actionEntrenarFragmentToEntrenarDetalle(
                coll
            )
            holder.viewDataBinding.root.findNavController().navigate(action)*/

        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class CalendarioViewHolder(val viewDataBinding: CalendarioItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.calendario_item
        }
    }
}