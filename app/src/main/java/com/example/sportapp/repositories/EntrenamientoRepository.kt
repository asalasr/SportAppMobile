package com.example.sportapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.services.EntrenamientoService

class EntrenamientoRepository (val application: Application){

    fun refreshData(callback: (List<AsignarDetallePlan>)->Unit, onError: (VolleyError)->Unit) {

        EntrenamientoService.getInstance(application).getAsignarDetallePlan({
            //Guardar la asignacion de Plan de la variable it en el almacén de datos AsignarDetallePlan
            callback(it)
        },
            onError
        )
    }
}