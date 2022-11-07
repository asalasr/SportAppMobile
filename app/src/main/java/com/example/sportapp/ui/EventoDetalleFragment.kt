package com.example.sportapp.ui

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sportapp.R
import com.example.sportapp.models.Eventos
import com.squareup.picasso.Picasso

class EventoDetalleFragment : Fragment() {

    companion object {
        fun newInstance() = EventoDetalleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_evento_detalle, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var evento: Eventos = arguments?.get("evento") as Eventos

        val title: TextView? = view?.findViewById(R.id.title)
        title?.text =  evento?.title

        val body: TextView? = view?.findViewById(R.id.body)
        body?.text = "Descripcion: " + evento?.body

        val urlImage: ImageView? = view?.findViewById(R.id.url_image)
        Picasso.get().load(evento?.url_image).into(urlImage);

        val dataTime: TextView? = view?.findViewById(R.id.datatime)
        dataTime?.text = "Fecha: " + evento?.datatime

        val state: TextView? = view?.findViewById(R.id.state)
        state?.text = "Estado: " + evento?.state

    }
}
