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
import com.example.sportapp.models.Evento
import com.squareup.picasso.Picasso
import kotlin.math.log

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
        var evento: Evento = arguments?.get("evento") as Evento

        val title: TextView? = view?.findViewById(R.id.title)
        title?.text = "Titulo: " + evento.event?.title

        val body: TextView? = view?.findViewById(R.id.body)
        body?.text = "Detalle: " + evento.event?.body

        val urlImage: ImageView? = view?.findViewById(R.id.url_image)
        Log.i("image ",evento.event.url_image)
        Picasso.get().load("https://imagenes.elpais.com/resizer/uGqCkDuwhDD0TKyl15KgnNhScvc=/1960x1103/ep01.epimg.net/tecnologia/imagenes/2017/08/04/actualidad/1501842915_812099_1502215531_noticia_fotograma.jpg").into(urlImage);


        val dataTime: TextView? = view?.findViewById(R.id.datatime)
        dataTime?.text = "Fecha: " + evento.event?.datatime

        val state: TextView? = view?.findViewById(R.id.state)
        state?.text = "Estado: " + evento.event?.state

    }
}
