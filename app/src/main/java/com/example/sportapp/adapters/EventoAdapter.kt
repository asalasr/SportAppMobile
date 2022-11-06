package com.example.sportapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.databinding.EventoItemBinding
import com.example.sportapp.models.Evento
import com.example.sportapp.ui.EventoListarFragmentDirections



class  EventoAdapter : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    var collectors: List<Evento> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): EventoViewHolder {
        val withDataBinding: EventoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            EventoViewHolder.LAYOUT,
            parent,
            false
        )
        return EventoViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.evento = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            Log.i("Clic en un dia", "se dio clic en el dia:" + collectors[position].register_date)
            val coll = collectors[position]
            Log.i("test", coll.toString())
            val action = EventoListarFragmentDirections.actionEventoFragmentToEventoDetalle(
                coll
            )
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class EventoViewHolder(val viewDataBinding: EventoItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.evento_item
        }
    }
}