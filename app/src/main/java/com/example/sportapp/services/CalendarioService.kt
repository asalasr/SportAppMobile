package com.example.sportapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportapp.models.Calendario
import com.example.sportapp.models.MisEventos
import org.json.JSONArray

class CalendarioService constructor(context: Context)  {
    companion object{
        const val BASE_URL_ENTRENAR= "https://sportapp.azure-api.net/planentrenamiento/"
        const val BASE_URL_EVENTOS= "https://gestorterceros.azurewebsites.net/api/v1/"

        var instance: CalendarioService? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CalendarioService(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getCalendarioFechas(onComplete:(resp:List<Calendario>)->Unit, onError: (error: VolleyError)->Unit) {

        Log.i("CalendarioService","Llego getCalendarioFechas Entrenar")
        val listReturn = mutableListOf<Calendario>()
        requestQueue.add(getRequest(BASE_URL_ENTRENAR,"asignar-detalle-plan/deportista",
            Response.Listener<String> { response ->

                val resp = JSONArray(response)
                for (i in 0 until resp.length()) {

                    val item = resp.getJSONObject(i)
                    if (item.getString("estado").equals("Planeado")) {
                        listReturn.add(i, Calendario(
                            ids = item.getString("id"),
                            fecha = item.getString("fechaInicio"),
                            actividad= "Rutina Diaria",
                            detalle= "Dia " + item.getString("numdia"),
                            state = item.getString("estado"),
                            )
                        )
                    }
                }
                onComplete(listReturn)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Calendario", it.message.toString())
            },
        ))

        Log.i("CalendarioService","Llego getCalendarioFechas Eventos")
        requestQueue.add(getRequest(BASE_URL_EVENTOS,"events/me",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    listReturn.add(i, Calendario(
                        ids = item.getJSONObject("event").getString("id"),
                        fecha = item.getJSONObject("event").getString("datatime").substring(0,10),
                        actividad= "Evento",
                        detalle= item.getJSONObject("event").getString("title"),
                        state = item.getJSONObject("event").getString("state")


                    ))}
                onComplete(listReturn)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Evento", it.message.toString())
            },
        ))

        Log.i("CalendarioService","Sale del endpoint")
    }

    private fun getRequest(url:String, path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        //return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        val stringRequest = object: StringRequest(Request.Method.GET, url + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "9920d31e-efa4-4b35-8d28-762bea2b6610"
                return headers
            }
        }
        return stringRequest
    }
}