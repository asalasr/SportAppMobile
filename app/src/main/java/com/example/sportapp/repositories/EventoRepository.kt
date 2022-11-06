package com.example.sportapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.sportapp.models.Evento
import com.example.sportapp.services.EventoService

class EventoRepository (val application: Application){

    fun refreshData(callback: (List<Evento>)->Unit, onError: (VolleyError)->Unit) {

        EventoService.getInstance(application).getEvento({
            callback(it)
        },
            onError
        )
    }
}