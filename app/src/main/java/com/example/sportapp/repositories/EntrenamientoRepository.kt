package com.example.sportapp.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.sportapp.adapters.EntrenamientoAdapter
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.services.EntrenamientoService
import com.example.sportapp.services.EntrenamientoService.Companion.getInstance
import android.content.Context

class EntrenamientoRepository (val application: Application){

    fun refreshData(callback: (List<AsignarDetallePlan>)->Unit, onError: (VolleyError)->Unit) {

        EntrenamientoService.getInstance(application).getAsignarDetallePlan({
            //Guardar la asignacion de Plan de la variable it en el almacén de datos AsignarDetallePlan
            callback(it)
        },
            onError
        )
    }

    fun putAsignarDetallePlan(
        asignarDetallePlan: AsignarDetallePlan,
        cbSuccess: (resp: Boolean) -> Unit,
        cbError: (resp: VolleyError) -> Unit
    ) {

        if(application!=null){
            EntrenamientoService.getInstance(application).putAsignarDetallePlan(asignarDetallePlan, {
                Log.i("EntrenamientoRepository", "Actualziacion realizada")
                cbSuccess(it)
            }, {
                Log.i("EntrenamientoRepository", "Error en la actualizacion")
                cbError(it)
            }
            )
        }

    }
}