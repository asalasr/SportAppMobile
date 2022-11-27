package com.example.sportapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.databinding.RutasItemBinding
import com.example.sportapp.models.Rutas
import com.example.sportapp.ui.RutasListarFragmentDirections

class RutasAdapter : RecyclerView.Adapter<RutasAdapter.RutasViewHolder>(){

    var collectors: List<Rutas> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutasAdapter.RutasViewHolder {
        val withDataBinding: RutasItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            RutasAdapter.RutasViewHolder.LAYOUT,
            parent,
            false
        )
        return RutasAdapter.RutasViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: RutasAdapter.RutasViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.rutas = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {

            // Navigate using that action
            //  holder.viewDataBinding.root.findNavController().navigate(action)
            Log.i("RutasAdapter", "se dio clic en el title:" + collectors[position].title)

            val coll = collectors[position]
            Log.i("EntrenamientoAdapter", coll.toString())
            val action = RutasListarFragmentDirections.actionRutasListarFragmentToRutasDetalle(
                coll
            )
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }
    class RutasViewHolder(val viewDataBinding: RutasItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.rutas_item
        }
    }
}