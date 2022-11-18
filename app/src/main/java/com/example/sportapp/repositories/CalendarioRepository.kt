package com.example.sportapp.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.sportapp.models.Calendario
import com.example.sportapp.services.CalendarioService

class CalendarioRepository (val application: Application){

    fun getCalendario(
        callback: (List<Calendario>)->Unit, onError: (VolleyError)->Unit
    ) {

        Log.i("CalendarioRepository", "Carga eventos inscritos")
        CalendarioService.getInstance(application).getCalendarioFechas({
            callback(it)
        },
            onError
        )
        Log.i("CalendarioRepository", "Fin de llamados")
    }
}