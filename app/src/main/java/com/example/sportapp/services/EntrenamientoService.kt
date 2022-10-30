package com.example.sportapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Header
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportapp.models.AsignarDetallePlan
import org.json.JSONArray
import org.json.JSONObject

class EntrenamientoService constructor(context: Context)  {
    companion object{
        const val BASE_URL= "https://sportapp.azure-api.net/"
        var instance: EntrenamientoService? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: EntrenamientoService(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAsignarDetallePlan(onComplete:(resp:List<AsignarDetallePlan>)->Unit, onError: (error: VolleyError)->Unit) {
        Log.i("NetworkServices","LLEGO ENDPOINT")


        requestQueue.add(getRequest("planentrenamiento/asignar-detalle-plan/deportista",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<AsignarDetallePlan>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)

                    //val idAsignarPlanId = item.getJSONArray("AsignarPlan")
                    //val PlanId: MutableList<Int> = ArrayList()
                    //for (j in 0 until idAsignarPlanId.length()) {
                      //  PlanId.add(j,idAsignarPlanId.getJSONObject(j).getString("id").toInt())
                    //}

                    list.add(i, AsignarDetallePlan(
                        ids = item.getString("id"),
                        idDeportista = item.getString("idDeportista"),
                        idDetallePlan = item.getString("idDetallePlan"),
                        numDia = item.getInt("numdia"),
                        marcaStreet = item.getString("marcaStreet"),
                        estado = item.getString("estado"),
                        fechaInicio = item.getString("fechaInicio"),
                        fechaFin = item.getString("fechaFin"),
                        calorias = item.getInt("calorias"),
                        velocidadMaxima = item.getInt("velocidadMaxima"),
                        distanciaRecorrida = item.getInt("distanciaRecorrida"),
                        idAsignarPlanId = item.getString("id")
                    )

                    )
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Entrenamiento", it.message.toString())
            },
        ))

    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        //return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        val stringRequest = object: StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "44ad47c7-6bb0-4a7d-bb6d-8cd0547d88fb"
                return headers
            }
        }
        return stringRequest
    }

    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}