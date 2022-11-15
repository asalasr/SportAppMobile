package com.example.sportapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.sportapp.models.DetallePlan
import com.example.sportapp.services.DetallePlanService

class DetallePlanRepository (val application: Application){

    fun refreshDataDetallePlan(callback: (List<DetallePlan>)->Unit, onError: (VolleyError)->Unit) {

        DetallePlanService.getInstance(application).getDetallePlan({
            callback(it)
        },
            onError
        )
    }
}