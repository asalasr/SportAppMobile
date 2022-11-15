package com.example.sportapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportapp.models.AsignarPlan
import com.example.sportapp.models.DetallePlan
import com.example.sportapp.models.Eventos
import org.json.JSONArray

class DetallePlanService constructor(context: Context) {

    companion object {
        const val BASE_URL = "https://sportapp.azure-api.net"
        var instance: DetallePlanService? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: DetallePlanService(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getDetallePlan(
        onComplete: (resp: List<DetallePlan>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        Log.i("NetworkServices", "LLEGO ENDPOINT DetallePlan")

        requestQueue.add(getRequest("/planentrenamiento/asignar-detalle-plan/deportista",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<DetallePlan>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    Log.i("NetworkServices","LLEGO ENDPOINT DetallePlan")
                    list.add(i, DetallePlan(
                            id = item.getString("id"),
                            idDeportista= item.getString("idDeportista"),
                            idDetallePlan= item.getString("idDetallePlan"),
                            numdia= item.getString("numdia"),
                            marcaStreet= item.getString("marcaStreet"),
                            estado= item.getString("estado"),
                            fechaInicio= item.getString("fechaInicio"),
                            fechaFin= item.getString("fechaFin"),
                            calorias= item.getString("calorias"),
                            velocidadMaxima= item.getString("velocidadMaxima"),
                            distanciaRecorrida= item.getString("distanciaRecorrida"),
                            AsignarPlan= AsignarPlan(
                                id = item.getJSONObject("AsignarPlan").getString("id"),
                                idDeportista= item.getJSONObject("AsignarPlan").getString("idDeportista"),
                                fechaInicio= item.getJSONObject("AsignarPlan").getString("fechaInicio"),
                                fechaFin= item.getJSONObject("AsignarPlan").getString("fechaFin"),
                                estado = item.getJSONObject("AsignarPlan").getString("estado"),
                            )
                        ))
                 }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get detalle", it.message.toString())
            },
        ))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        //return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        val stringRequest = object: StringRequest(Request.Method.GET, DetallePlanService.BASE_URL + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "f45d2c24-9f5b-4809-901f-8f095bc58534"
                return headers
            }
        }
        return stringRequest
    }


}