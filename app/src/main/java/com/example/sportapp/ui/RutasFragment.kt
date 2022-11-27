package com.example.sportapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportapp.R


class RutasFragment : Fragment() {

    companion object {
        fun newInstance() = RutasFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_rutas, container, false)
        Log.i("EntrenarDetalleFragment","onCreateView - ENTRO A onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i("RutasFragment","onActivityCreated inicializa viewmodel")
    }
}