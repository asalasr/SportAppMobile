package com.example.sportapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.sportapp.R
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.models.Rutas
import com.example.sportapp.viewmodels.EntrenamientoViewModel
import com.example.sportapp.viewmodels.RutasViewModel
import com.squareup.picasso.Picasso


class RutasDetalleFragment : Fragment() {
    private lateinit var rutasViewModelClass: RutasViewModel
    private lateinit var ruta: Rutas

    companion object {
        fun newInstance() = RutasDetalleFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_rutas_detalle, container, false)
        Log.i("RutasDetalleFragment","onCreateView - ENTRO A onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("RutasDetalleFragment","onActivityCreated inicializa viewmodel")



        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        rutasViewModelClass = ViewModelProvider(this, RutasViewModel.Factory(activity.application)).get(
            RutasViewModel::class.java
        )

        ruta= arguments?.get("plan") as Rutas

        val entrenarDia: TextView? = view?.findViewById(R.id.title)
        entrenarDia!!.text= "DIA "+ruta.title
        val entrenarEstado: TextView? = view?.findViewById(R.id.linkGoogle)
        entrenarEstado!!.text= ruta.linkGoogle

        val urlImage: ImageView? = view?.findViewById(R.id.urlImage)
        Picasso.get().load(ruta?.urlImage).into(urlImage);

    }
}