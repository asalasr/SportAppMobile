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
        Log.i("EntrenamientoService","Llego getAsignarDetallePlan")
        requestQueue.add(getRequest("planentrenamiento/asignar-detalle-plan/deportista",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                var cal: Double= 0.0
                var dist: Double= 0.0
                var vel: Double= 0.0
                var finicio : String
                var ffin : String
                val resp = JSONArray(response)
                val list = mutableListOf<AsignarDetallePlan>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)

                    //val idAsignarPlanId = item.getJSONArray("AsignarPlan")
                    //val PlanId: MutableList<Int> = ArrayList()
                    //for (j in 0 until idAsignarPlanId.length()) {
                      //  PlanId.add(j,idAsignarPlanId.getJSONObject(j).getString("id").toInt())
                    //}
                    cal= 0.0
                    if (!item.getString("calorias").equals("null")) {
                         cal = item.getDouble("calorias")
                    }

                    dist= 0.0
                    if (!item.getString("distanciaRecorrida").equals("null")) {
                        dist = item.getDouble("distanciaRecorrida")
                    }

                    vel= 0.0
                    if (!item.getString("velocidadMaxima").equals("null")) {
                        vel = item.getDouble("velocidadMaxima")
                    }

                    finicio = ""
                    if (!item.getString("fechaInicio").equals("null")) {
                        finicio = item.getString("fechaInicio")
                    }

                    ffin = ""
                    if (!item.getString("fechaFin").equals("null")) {
                        ffin = item.getString("fechaFin")
                    }

                    list.add(i, AsignarDetallePlan(
                        ids = item.getString("id"),
                        idDeportista = item.getString("idDeportista"),
                        idDetallePlan = item.getString("idDetallePlan"),
                        numDia = item.getString("numdia"),
                        marcaStreet = item.getString("marcaStreet"),
                        estado = item.getString("estado"),
                        fechaInicio = finicio,
                        fechaFin = ffin,
                        calorias = cal,
                        velocidadMaxima = vel,
                        distanciaRecorrida = dist,
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

    fun putAsignarDetallePlan(
        asignarDetallePlan: AsignarDetallePlan,
        onComplete: (resp: Boolean) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        val postParams = mapOf<String, Any>(
            "fechaInicio" to asignarDetallePlan.fechaInicio,
            "fechaFin" to asignarDetallePlan.fechaFin,
            "calorias" to asignarDetallePlan.calorias,
            "velocidadMaxima" to asignarDetallePlan.velocidadMaxima,
            "distanciaRecorrida" to asignarDetallePlan.distanciaRecorrida,
        )
        Log.i("EntrenamientoService","putAsignarDetallePlan - ejecuta Put")
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.ids)
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.fechaInicio)
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.fechaFin)
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.calorias)
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.velocidadMaxima)
        Log.i("EntrenamientoService","putAsignarDetallePlan - ids"+ asignarDetallePlan.distanciaRecorrida)

        requestQueue.add (
            putRequest("planentrenamiento/asignar-detalle-plan/"+asignarDetallePlan.ids, JSONObject(postParams),
                Response.Listener<JSONObject> { response ->

                    var item: JSONObject? = null

                    item = response
                    Log.d("Response", item.toString())
                    onComplete(true)
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
        Log.i("EntrenamientoService","putAsignarDetallePlan - Put ejecutado")
    }




    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        //return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        val stringRequest = object: StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "9920d31e-efa4-4b35-8d28-762bea2b6610"
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