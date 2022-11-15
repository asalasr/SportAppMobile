package com.example.sportapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.databinding.DetallePlanItemBinding
import com.example.sportapp.databinding.EventoItemBinding
import com.example.sportapp.models.DetallePlan



class DetallePlanAdapter : RecyclerView.Adapter<DetallePlanAdapter.DetallePlanViewHolder>() {

    var collectors: List<DetallePlan> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: DetallePlanAdapter.DetallePlanViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.detallePlan = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val coll = collectors[position]
            Log.i("DetallePlanAdapter", coll.toString())
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class DetallePlanViewHolder(val viewDataBinding: DetallePlanItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detalle_plan_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): DetallePlanAdapter.DetallePlanViewHolder {
        val withDataBinding: DetallePlanItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DetallePlanAdapter.DetallePlanViewHolder.LAYOUT,
            parent,
            false
        )
        return DetallePlanAdapter.DetallePlanViewHolder(withDataBinding)
    }
}