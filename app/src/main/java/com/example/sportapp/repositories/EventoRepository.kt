package com.example.sportapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.sportapp.models.Eventos
import com.example.sportapp.services.EventoService

class EventoRepository (val application: Application){

    fun refreshDataEventos(callback: (List<Eventos>)->Unit, onError: (VolleyError)->Unit) {

        EventoService.getInstance(application).getEventos({
            callback(it)
        },
            onError
        )
    }
}