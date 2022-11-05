package com.example.sportapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.databinding.EntrenarItemBinding
import com.example.sportapp.ui.EntrenarListarFragmentDirections



class EntrenamientoAdapter : RecyclerView.Adapter<EntrenamientoAdapter.EntrenamientoViewHolder>() {

    var collectors: List<AsignarDetallePlan> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): EntrenamientoViewHolder {
        val withDataBinding: EntrenarItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            EntrenamientoViewHolder.LAYOUT,
            parent,
            false
        )
        return EntrenamientoViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: EntrenamientoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.asignarDetallePlan = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {

            // Navigate using that action
            //  holder.viewDataBinding.root.findNavController().navigate(action)
            Log.i("EntrenamientoAdapter", "se dio clic en el dia:" + collectors[position].numDia)
            val coll = collectors[position]
            Log.i("EntrenamientoAdapter", coll.toString())
            val action = EntrenarListarFragmentDirections.actionEntrenarFragmentToEntrenarDetalle(
                coll
            )
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class EntrenamientoViewHolder(val viewDataBinding: EntrenarItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.entrenar_item
        }
    }
}