package com.example.sportapp.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.sportapp.models.Calendario
import com.example.sportapp.services.CalendarioService
import com.example.sportapp.services.EntrenamientoService
import com.example.sportapp.services.EventoService

class CalendarioRepository (val application: Application){

    fun getCaledario(
        callback: (List<Calendario>)->Unit, onError: (VolleyError)->Unit
    ) {

        Log.i("CalendarioRepository", "Carga eventos inscritos")
        CalendarioService.getInstance(application).getCalendarioFechas({
            //Guardar la asignacion de Plan de la variable it en el almacén de datos AsignarDetallePlan
            callback(it)
        },
            onError
        )

        /*
        Log.i("CalendarioRepository", "Carga eventos inscritos")
        EventoService.getInstance(application).getCalendarioMisEventos({
            //Guardar la asignacion de Plan de la variable it en el almacén de datos AsignarDetallePlan
            callback(it)
        },
            onError
        )

        Log.i("CalendarioRepository", "Carga fechas de rutinas")
        EntrenamientoService.getInstance(application).getCalendarioFechas({
            //Guardar la asignacion de Plan de la variable it en el almacén de datos AsignarDetallePlan
            callback(it)
        },
            onError
        )*/

        Log.i("CalendarioRepository", "Fin de llamados")
    }
}