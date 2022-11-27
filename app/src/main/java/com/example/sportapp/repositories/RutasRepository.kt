package com.example.sportapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.sportapp.models.Rutas
import com.example.sportapp.services.RutasService

class RutasRepository (val application: Application){

    fun refreshDataRutas(callback: (List<Rutas>)->Unit, onError: (VolleyError)->Unit) {

        RutasService.getInstance(application).getRutas({
            //Guardar las rutas de la variable it en el almacén de datos Rutas
            callback(it)
        },
            onError
        )
    }
}