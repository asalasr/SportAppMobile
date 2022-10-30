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
        var collector: AsignarDetallePlan = arguments?.get("plan") as AsignarDetallePlan

        val name: TextView? = view?.findViewById(R.id.collectorName)
        name!!.text= "Nombre:  "+collector.numDia
        val email: TextView? = view?.findViewById(R.id.collectorEmail)
        email!!.text= "Email:  "+collector.marcaStreet
        val phone: TextView? = view?.findViewById(R.id.collectorPhone)
        phone!!.text= "Phone:  "+collector.estado

    }

}