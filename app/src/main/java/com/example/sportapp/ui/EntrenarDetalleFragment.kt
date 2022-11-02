package com.example.sportapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sportapp.R
import com.example.sportapp.models.AsignarDetallePlan


class EntrenarDetalleFragment : Fragment() {

    companion object {
        fun newInstance() = EntrenarDetalleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_entrenar_detalle, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var plan: AsignarDetallePlan = arguments?.get("plan") as AsignarDetallePlan

        val entrenarDia: TextView? = view?.findViewById(R.id.entrenarDia)
        entrenarDia!!.text= "Dia: "+plan.numDia
        val entrenarEstado: TextView? = view?.findViewById(R.id.entrenarEstado)
        entrenarEstado!!.text= "Estado: "+plan.estado
        val entrenarMarcaStreet: TextView? = view?.findViewById(R.id.entrenarMarcaStreet)
        entrenarMarcaStreet!!.text= "Lugar: "+plan.marcaStreet
        val entrenarFechaInicio: TextView? = view?.findViewById(R.id.entrenarFechaInicio)
        entrenarFechaInicio!!.text= "Fecha Inicio: "+plan.fechaInicio
        val entrenarFechaFin: TextView? = view?.findViewById(R.id.entrenarFechaFin)
        entrenarFechaFin!!.text= "Fecha Fin: "+plan.fechaFin
        val entrenarCalorias: TextView? = view?.findViewById(R.id.entrenarCalorias)
        entrenarCalorias!!.text= "Calorias: "+plan.calorias
        val entrenarVelocidadMaxima: TextView? = view?.findViewById(R.id.entrenarVelocidadMaxima)
        entrenarVelocidadMaxima!!.text= "Velocidad: "+plan.velocidadMaxima
        val entrenarDistanciaRecorrida: TextView? = view?.findViewById(R.id.entrenarDistanciaRecorrida)
        entrenarDistanciaRecorrida!!.text= "Distancia: "+plan.distanciaRecorrida

    }

}